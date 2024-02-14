package net.wiredtomato.perception.systems.rendering.shapes.geometry

import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.cache.MODEL_CACHE
import net.wiredtomato.perception.systems.cache.addCacheInvalidationTarget
import net.wiredtomato.perception.systems.util.mutableConcurrentMapOf

object GeometricShapes {
    private var shapes = mutableConcurrentMapOf<Identifier, DrawableGeometricShape>()

    fun registerShape(id: Identifier, shape: DrawableGeometricShape) {
        shapes.putIfAbsent(id, shape)
    }

    internal fun init() {
        addCacheInvalidationTarget(MODEL_CACHE) {
            shapes = mutableConcurrentMapOf()
        }
    }

    operator fun get(id: Identifier): DrawableGeometricShape? = shapes[id]
}