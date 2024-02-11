package net.wiredtomato.perception.systems.rendering.shapes.geometry

import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.systems.cache.MODEL_CACHE
import net.wiredtomato.perception.systems.cache.invalidateCaches
import net.wiredtomato.perception.systems.model.loading.GeometryLoader
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.WaveFrontLoader

object GeometricShapes: SimpleSynchronousResourceReloadListener {
    private var shapes = mutableMapOf<Identifier, DrawableGeometricShape>()
    private val loaders = mutableMapOf<Identifier, GeometryLoader>()

    override fun reload(manager: ResourceManager) {
        invalidateCaches(MODEL_CACHE)
        shapes = mutableMapOf()
        val loaderFileExtensions = loaders.map { (_, loader) -> loader.fileExtensions() }.flatten()

        manager.findResources("models/shape") {
            val fileExt = it.path.split(".").lastOrNull()
            loaderFileExtensions.contains(fileExt) || it.path.endsWith("obj")
        }.forEach { (id, resource) ->
            loaders.forEach { (_, loader) ->
                val fileExt = id.path.split(".").lastOrNull()
                if (loader.fileExtensions().contains(fileExt)) loader.loadGeometry(manager, id)
            }

            resource.open().use {
                val bytes = it.readBytes()
                val data = bytes.decodeToString()
                val loadedShapes = WaveFrontLoader.load(data)
                val idStripped = Identifier(id.namespace, id.path.removePrefix("models/shape/").removeSuffix(".obj"))
                loadedShapes.forEach { (name, shape) ->
                    val path = if (idStripped.path == name) idStripped.path else "${idStripped.path}.$name"
                    val shapeId = Identifier(idStripped.namespace, path)
                    Perception.logger.info("loaded $shapeId")
                    shapes[shapeId] = shape
                }

                //shapes[idStripped] = shape
            }
        }
    }

    fun registerLoader(id: Identifier, loader: GeometryLoader) {
        loaders.putIfAbsent(id, loader)
    }

    operator fun get(id: Identifier): DrawableGeometricShape? = shapes[id]

    override fun getFabricId(): Identifier = Perception.id("shape_loader")
}