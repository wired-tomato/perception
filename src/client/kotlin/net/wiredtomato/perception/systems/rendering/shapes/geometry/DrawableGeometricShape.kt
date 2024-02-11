package net.wiredtomato.perception.systems.rendering.shapes.geometry

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat.DrawMode
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.shapes.Drawable
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import org.joml.Vector2f
import org.joml.Vector3f

interface DrawableGeometricShape: Drawable, GeometricShape {
    class ComplexMutable(
        val vertices: MutableList<Vector3f>,
        val uvs: MutableList<Vector2f>,
        val faces: MutableList<DrawableGeometricShape>,
        var drawMode: DrawMode
    ): DrawableGeometricShape {
        override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
            faces.forEach { it.draw(vertexConsumer, vertexPlacer, stack, color, light) }
        }

        override fun vertices(): List<Vector3f> = vertices
        override fun uvs(): List<Vector2f> = uvs
        override fun faces(): List<DrawableGeometricShape> = faces
        override fun drawMode(): DrawMode = drawMode

        fun toImmutable() = ComplexImmutable(vertices.toList(), uvs.toList(), faces.toList(), drawMode)
    }

    class ComplexImmutable(
        val vertices: List<Vector3f>,
        val uvs: List<Vector2f>,
        val faces: List<DrawableGeometricShape>,
        val drawMode: DrawMode
    ): DrawableGeometricShape {
        override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
            faces.forEach { it.draw(vertexConsumer, vertexPlacer, stack, color, light) }
        }

        override fun vertices(): List<Vector3f> = vertices
        override fun uvs(): List<Vector2f> = uvs
        override fun faces(): List<DrawableGeometricShape> = faces
        override fun drawMode(): DrawMode = drawMode

        fun toMutable() = ComplexMutable(vertices.toMutableList(), uvs.toMutableList(), faces.toMutableList(), drawMode)
    }

    companion object {
        fun mutable() = ComplexMutable(mutableListOf(), mutableListOf(), mutableListOf(), DrawMode.TRIANGLES)
    }
}