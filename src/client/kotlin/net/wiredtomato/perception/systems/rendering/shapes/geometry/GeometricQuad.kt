package net.wiredtomato.perception.systems.rendering.shapes.geometry

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.shapes.Triangle
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import org.joml.Vector3f

interface GeometricQuad: DrawableGeometricShape {
    fun v0(): Vector3f
    fun v1(): Vector3f
    fun v2(): Vector3f
    fun v3(): Vector3f

    override fun vertices(): List<Vector3f> = listOf(v0(), v1(), v2(), v3())

    override fun faces(): List<GeometricShape> = listOf(this)
    override fun isQuads() = true

    fun drawAsTris(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int)
    fun tris(): List<GeometricTriangle> {
        val uvs = uvs()
        val (t0, t1) = if (uvs.size >= 4) {
            Pair(
                Triangle(v0(), v1(), v2(), uvs[0], uvs[1], uvs[2]),
                Triangle(v3(), v1(), v2(), uvs[3], uvs[1], uvs[2])
            )
        } else Pair(
            Triangle(v0(), v1(), v2()),
            Triangle(v3(), v1(), v2())
        )

        return listOf(t0, t1)
    }

    override fun drawMode(): VertexFormat.DrawMode = VertexFormat.DrawMode.QUADS
}