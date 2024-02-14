package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.cache.MODEL_CACHE
import net.wiredtomato.perception.systems.cache.memoize
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import net.wiredtomato.perception.systems.rendering.util.normalizeFromCircleCenter
import org.joml.Vector3f

class IcoSphere(var center: Vector3f, var radius: Float, var subdivisions: Int): Drawable {
    override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        stack.scale(radius, radius, radius)
        val last = stack.peek().model
        computeAndCacheTris(center, radius, subdivisions).forEach {
            it.draw(vertexConsumer, vertexPlacer, last, color, light)
        }
        val radiusInv = 1 / radius
        stack.scale(radiusInv, radiusInv, radiusInv)
    }

    override fun drawMode(): VertexFormat.DrawMode = VertexFormat.DrawMode.TRIANGLES

    companion object {
        private val computeTrisAndCache = memoize(MODEL_CACHE, this::computeTris)
        fun computeAndCacheTris(center: Vector3f, radius: Float, subdivisions: Int) = computeTrisAndCache(center, radius, subdivisions)

        fun computeTris(center: Vector3f, radius: Float, subdivisions: Int): List<Triangle> {
            val icosahedron = Icosahedron.RAW_ICOSAHEDRON()
            val tris = icosahedron.faces().map { it as Triangle }

            if (subdivisions <= 1) return tris

            val temp = mutableListOf<Triangle>()
            tris.forEach {
                temp += it.subdiv(subdivisions - 1, normalizeFromCircleCenter(center, radius))
            }

            return temp
        }
    }
}