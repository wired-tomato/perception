package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.model.loading.GeometryLoader
import net.wiredtomato.perception.systems.model.loading.MappedGeometry
import net.wiredtomato.perception.systems.model.loading.ifPresentRun
import net.wiredtomato.perception.systems.model.loading.mappedTo
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjMaterialLibrary
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjTokenizer
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.exceptions.ObjParseException
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

object ObjLoader: GeometryLoader {
    private val loaded = mutableMapOf<Identifier, GeometricObj>()

    override fun loadGeometry(manager: ResourceManager, id: Identifier) {
        val fileExt = id.path.split(".").last()
        if (fileExt == "obj") loadObj(manager, id)
        else loadMtl(manager, id)
    }

    override fun getLoaded() = loaded.map { (id, obj) -> obj mappedTo id }

    fun loadObj(manager: ResourceManager, id: Identifier): GeometricObj? {
        val mesh = manager.getResource(id) ifPresentRun {
            val data = it.open()
            val tokenizer = ObjTokenizer(data)
            val builder: GeometricObj.Builder = GeometricObj.builder()

            var line: List<String> = tokenizer.readAndSplitLine(true) ?: listOf()
            do {
                if (line.isEmpty()) continue

                when (line[0]) {
                    "mtllib" -> {
                        if (builder.mtllib() != null) continue

                        val lib = line[1]
                        if (lib.contains(":")) {
                            builder.mtllib(loadMtl(manager, Identifier(lib)))
                        } else builder.mtllib(loadMtl(manager, Identifier(id.namespace, "${id.path}$lib")))
                    }
                    "usemtl" -> {
                        val mat = line.subList(1, line.size).reduce { a, b -> "$a $b" }
                        val nMat = builder.mtllib()!!.materials[mat]
                        if (nMat != builder.mesh()?.material()) {
                            if (builder.mesh()?.material() != null && builder.mesh()?.faces()?.size == 0) {
                                builder.mesh()!!.material(nMat!!)
                            } else builder.newMesh(null)
                        }
                    }
                    "v" -> {
                        builder.mesh()!!.vertex(loadVec3(line))
                    }
                    "vt" -> {
                        builder.mesh()!!.uv(loadVec2(line))
                    }
                    "vn" -> {
                        builder.mesh()!!.normal(loadVec3(line))
                    }
                    "f" -> {
                        val indices = loadFaceIndices(line)
                        builder.mesh()!!.face(indices.first, indices.second, indices.third)
                    }
                    "o", "g" -> {
                        if (builder.mesh() != null && builder.mesh()?.name() == null)
                            builder.mesh()!!.name(line[1])
                        else builder.newMesh(line[1])
                    }
                    "s" -> {
                        builder.mesh()!!.shadingGroup(line[1])
                    }
                }
            } while (tokenizer.readAndSplitLine(true)?.also { split -> line = split } != null)

            tokenizer.close()

            builder.build()
        }

        return mesh
    }

    fun loadMtl(manager: ResourceManager, id: Identifier): ObjMaterialLibrary {
        val library = manager.getResource(id) ifPresentRun {
            ObjTokenizer(it.open()).use { tok ->
                ObjMaterialLibrary(tok)
            }
        }
        
        return library!!
    }

    inline fun <reified T> parse(line: List<String>): T {
        val t: Any = if (T::class == Vector2f::class) loadVec2(line)
        else if (T::class == Vector3f::class) loadVec3(line)
        else if (T::class == Vector4f::class) loadVec4(line)
        else if (T::class == Boolean::class) { listOf("1", "on", "true").contains(line[1].lowercase()) }
        else throw ObjParseException("Attempted to parse unparseable class ${T::class.qualifiedName}")

        return t as T
    }

    fun loadVec2(line: List<String>) =
        Vector2f(line[2].toFloat(), line[2].toFloat())

    fun loadVec3(line: List<String>) =
        Vector3f(line[1].toFloat(), line[2].toFloat(), line[3].toFloat())

    fun loadVec4(line: List<String>) {}

    fun loadFaceIndices(line: List<String>): Triple<List<Int>, List<Int>, List<Int>> {
        val indices = line.subList(1, line.size).map { it.split("/") }
        val size = indices[0].size
        return when (size) {
            1 -> {
                val vertexIndices = indices[0].map { it.toInt() - 1 }
                Triple(vertexIndices, listOf(), listOf())
            }
            2 -> {
                val vertexIndices = indices[0].map { it.toInt() - 1 }
                val uvIndices = indices[1].map { it.toInt() - 1 }
                Triple(vertexIndices, uvIndices, listOf())
            }
            else -> {
                val vertexIndices = indices[0].map { it.toInt() - 1 }
                val uvIndices = indices[1].map { it.toInt() - 1 }
                val normalIndices = indices[2].map { it.toInt() - 1 }
                Triple(vertexIndices, uvIndices, normalIndices)
            }
        }
    }

    override fun fileExtensions() = listOf("obj", "mtl")
}