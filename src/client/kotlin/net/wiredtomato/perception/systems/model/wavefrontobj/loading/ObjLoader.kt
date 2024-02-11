package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.model.loading.GeometryLoader
import net.wiredtomato.perception.systems.model.loading.MappedGeometry
import net.wiredtomato.perception.systems.model.loading.ifPresentRun
import net.wiredtomato.perception.systems.model.loading.mappedTo
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

    fun loadObj(manager: ResourceManager, id: Identifier): List<MappedGeometry> {
        manager.getResource(id) ifPresentRun {
            val data = it.open()
            val tokenizer = ObjTokenizer(data)
            val objects = mutableMapOf<String, GeometricObj>()
            var builder: GeometricObj.Builder? = null
            var name: String? = null

            var line: List<String> = tokenizer.readAndSplitLine(true) ?: listOf()
            do {
                if (line.isEmpty()) continue

                when (line[0]) {
                    "v" -> {
                        builder!!.vertex(loadVec3(line))
                    }
                    "vt" -> {
                        builder!!.uv(loadVec2(line))
                    }
                    "vn" -> {
                        builder!!.normal(loadVec3(line))
                    }
                    "f" -> {
                        val indices = loadFaceIndices(line)
                        builder!!.face(indices.first, indices.second, indices.third)
                    }
                    "o", "g" -> {
                        if (builder == null) {
                            builder = GeometricObj.builder()
                            name = line[1]
                        } else {
                            objects[name!!] = builder.build()
                            builder = GeometricObj.builder()
                            name = line[1]
                        }
                    }
                    "s" -> {
                        builder!!.smoothShading(line[1].toBoolean())
                    }
                }
            } while (tokenizer.readAndSplitLine(true)?.also { split -> line = split } != null)

            data.close()
        }

        TODO()
    }

    fun loadMtl(manager: ResourceManager, id: Identifier): List<MappedGeometry> {
        TODO()
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
        return if (size == 1) {
            val vertexIndices = indices[0].map { it.toInt() - 1 }
            Triple(vertexIndices, listOf(), listOf())
        } else if (size == 2) {
            val vertexIndices = indices[0].map { it.toInt() - 1 }
            val uvIndices = indices[1].map { it.toInt() - 1 }
            Triple(vertexIndices, uvIndices, listOf())
        } else {
            val vertexIndices = indices[0].map { it.toInt() - 1 }
            val uvIndices = indices[1].map { it.toInt() - 1 }
            val normalIndices = indices[2].map { it.toInt() - 1 }
            Triple(vertexIndices, uvIndices, normalIndices)
        }
    }

    override fun fileExtensions() = listOf("obj", "mtl")
}