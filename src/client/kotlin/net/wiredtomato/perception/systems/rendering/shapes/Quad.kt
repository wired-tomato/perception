package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.vector.u
import net.wiredtomato.perception.systems.math.vector.v
import net.wiredtomato.perception.systems.math.swizzling.xy
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricQuad
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricTriangle
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import org.joml.Vector2f
import org.joml.Vector3f

open class Quad(
    val v0: Vector3f,
    val v1: Vector3f,
    val v2: Vector3f,
    val v3: Vector3f,
    val uv0: Vector2f,
    val uv1: Vector2f,
    val uv2: Vector2f,
    val uv3: Vector2f
): GeometricQuad {
    constructor(v0: Vector3f, v1: Vector3f, v2: Vector3f, v3: Vector3f):
            this(v0, v1, v2, v3, v0.xy(), v1.xy(), v2.xy(), v3.xy())

    @Suppress("duplicates")
    override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        val last = stack.peek().model
        vertexPlacer.place(vertexConsumer, last, v0.x, v0.y, v0.z, color.r, color.g, color.b, color.a, uv0.u, uv0.v, light)
        vertexPlacer.place(vertexConsumer, last, v1.x, v1.y, v1.z, color.r, color.g, color.b, color.a, uv1.u, uv1.v, light)
        vertexPlacer.place(vertexConsumer, last, v2.x, v2.y, v2.z, color.r, color.g, color.b, color.a, uv2.u, uv2.v, light)
        vertexPlacer.place(vertexConsumer, last, v3.x, v3.y, v3.z, color.r, color.g, color.b, color.a, uv3.u, uv3.v, light)
    }

    override fun drawAsTris(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        tris().forEach { it.draw(vertexConsumer, vertexPlacer, stack, color, light) }
    }

    override fun v0(): Vector3f = v0
    override fun v1(): Vector3f = v1
    override fun v2(): Vector3f = v2
    override fun v3(): Vector3f = v3

    override fun uvs(): List<Vector2f> = listOf(uv0, uv1, uv2, uv3)
    override fun tris(): List<GeometricTriangle> = listOf(Triangle(v0, v1, v2, uv0, uv1, uv2), Triangle(v3, v1, v2, uv3, uv1, uv2))
}