package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShapes
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer

class Icosahedron(var radius: Float): Drawable {
    private val icosahedron = RAW_ICOSAHEDRON()

    override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        stack.scale(radius, radius, radius)
        icosahedron.draw(vertexConsumer, vertexPlacer, stack, color, light)
        val radiusInv = 1 / radius
        stack.scale(radiusInv, radiusInv, radiusInv)
    }

    override fun drawMode(): VertexFormat.DrawMode = VertexFormat.DrawMode.TRIANGLES

    companion object {
        val RAW_ICOSAHEDRON = { GeometricShapes[Perception.id("icosahedron")]!! }
    }
}