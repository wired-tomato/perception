package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin
import net.fabricmc.fabric.api.client.model.loading.v1.ModelResolver
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.model.UnbakedModel
import net.minecraft.resource.Resource
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.systems.cache.MODEL_CACHE
import net.wiredtomato.perception.systems.cache.invalidateCaches
import net.wiredtomato.perception.systems.cache.memoize
import net.wiredtomato.perception.systems.model.loading.GeometryLoader
import net.wiredtomato.perception.systems.model.loading.ifPresentRun
import net.wiredtomato.perception.systems.model.loading.mappedTo
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjMaterialLibrary
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjTokenizer
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.exceptions.ObjParseException
import net.wiredtomato.perception.systems.util.KConsumer
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

object ObjLoader: ModelLoadingPlugin, GeometryLoader {
    private val loaded = mutableMapOf<Identifier, GeometricObj>()
    val OBJ_MARKER = Perception.id("obj").toString()

    override fun onInitializeModelLoader(ctx: ModelLoadingPlugin.Context) {
        invalidateCaches(MODEL_CACHE)
        findModels(ctx::addModels)
        ctx.resolveModel().register(Resolver())
    }

    fun findModels(addModel: KConsumer<Identifier>) {
        val manager = MinecraftClient.getInstance().resourceManager
        manager.findResources("models/misc") { id ->
            if (id.path.endsWith(".json")) {
                manager.getResource(id).ifPresent {
                    if (loadJsonModel(id, it) != null) addModel(id)
                }
            }
            return@findResources true
        }
    }

    fun loadJsonModel(id: Identifier, resource: Resource): JsonObject? {
        runCatching {
            val json = JsonParser.parseReader(resource.openBufferedReader()).asJsonObject
            if (id.namespace == "perception") {
                Perception.logger.info(Gson().toJson(json) + "\n\n${json.has(OBJ_MARKER)}")
            }
            if (json.has(OBJ_MARKER)) return json
        }.onFailure {
            Perception.logger.error("Error loading obj model from models/misc: $id", it)
        }

        return null
    }

    override fun loadGeometry(manager: ResourceManager, id: Identifier) {
        loadObj(manager, id)
    }

    val loadUnbaked: (manager: ResourceManager, modelId: Identifier, settings: ModelSettings) -> Result<UnbakedObjModel> =
        memoize(MODEL_CACHE) { manager, modelId, settings ->
            runCatching {
                val geometry = loadObj(manager, modelId) ?: return@memoize Result.failure(ObjParseException("Failed to parse model with id: $modelId"))
                UnbakedObjModel(
                    settings,
                    geometry
                )
            }
        }

    fun tryLoadSettings(json: JsonObject): Result<ModelSettings> {
        return runCatching {
            Perception.logger.info("S_S")
            val objId = Identifier(JsonHelper.getString(json, "model"))
            ModelSettings(
                objId,
                JsonHelper.getBoolean(json, "automaticCulling", true),
                JsonHelper.getBoolean(json, "shadeQuads", true),
                JsonHelper.getBoolean(json, "flipV", true),
                JsonHelper.getBoolean(json, "emissivrAmbient", true),
                JsonHelper.getString(json, "mtlOverride", null)
            )
        }
    }

    override fun getLoaded() = loaded.map { (id, obj) -> obj mappedTo id }

    fun loadObj(manager: ResourceManager, id: Identifier): GeometricObj? {
        val mesh = manager.getResource(id) ifPresentRun {
            val data = it.open()
            val tokenizer = ObjTokenizer(data)
            val builder: GeometricObj.Builder = GeometricObj.builder()

            var line: List<String>? = tokenizer.readAndSplitLine(true)
            do {
                if (line.isNullOrEmpty()) continue

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
            } while (tokenizer.readAndSplitLine(true).also { split -> line = split } != null)

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

    fun loadVec4(line: List<String>) =
        Vector4f(line[1].toFloat(), line[2].toFloat(), line[3].toFloat(), line[4].toFloat())

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

    class Resolver: ModelResolver {
        private lateinit var currentId: Identifier

        override fun resolveModel(context: ModelResolver.Context): UnbakedModel? {
            val fid = context.id()
            currentId = fid
            val id = Identifier(fid.namespace, "models/${fid.path}.json")
            val manager = MinecraftClient.getInstance().resourceManager
            val res = manager.getResource(id).get()
            val resource = res
            Perception.logger.info("A_S")
            val json = loadJsonModel(id, resource) ?: return null
            Perception.logger.info("V_S")
            return tryLoadSettings(json).mapCatching {
                Perception.logger.info("Loading: $id")
                return@mapCatching loadUnbaked(manager, Identifier(JsonHelper.getString(json, "model")), it).getOrThrow()
            }.getOrNull()
        }

        private fun reportError(throwable: Throwable) {
            Perception.logger.error("Error loading obj model: $currentId", throwable)
        }
    }
}