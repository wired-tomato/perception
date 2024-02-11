package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.copy
import net.wiredtomato.perception.systems.math.xy
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricTriangle
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import net.wiredtomato.perception.systems.util.KFunc
import org.joml.Matrix4f
import org.joml.Vector2f
import org.joml.Vector3f

class Triangle(var v0: Vector3f, var v1: Vector3f, var v2: Vector3f, var uv0: Vector2f, var uv1: Vector2f, var uv2: Vector2f):
    GeometricTriangle {
    constructor(v0: Vector3f, v1: Vector3f, v2: Vector3f): this(v0, v1, v2, Vector2f(v0.x, v0.y), Vector2f(v1.x, v1.y), Vector2f(v2.x, v2.y))

    override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        val last = stack.peek().model
        draw(vertexConsumer, vertexPlacer, last, color, light)
    }

    @Suppress("duplicates")
    fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, last: Matrix4f, color: RGBA, light: Int) {
        vertexPlacer.place(vertexConsumer, last, v0.x, v0.y, v0.z, color.r, color.g, color.b, color.a, uv0.x, uv0.y, light)
        vertexPlacer.place(vertexConsumer, last, v1.x, v1.y, v1.z, color.r, color.g, color.b, color.a, uv1.x, uv1.y, light)
        vertexPlacer.place(vertexConsumer, last, v2.x, v2.y, v2.z, color.r, color.g, color.b, color.a, uv2.x, uv2.y, light)
    }

    fun subdiv(subdivisions: Int, normalize: KFunc<Vector3f, Vector3f> = { it }): List<Triangle> {
        if (subdivisions <= 0) return listOf(this)

        var last = subdiv(normalize).toMutableList()
        for (i in 2..subdivisions) {
            val current = last
            last = mutableListOf()
            current.forEach {
                last += it.subdiv(normalize)
            }
        }

        return last
    }

    fun subdiv(normalize: KFunc<Vector3f, Vector3f> = { it }): List<Triangle> {
        val v01 = normalize(v0.add(v1, Vector3f()).div(2f))
        val v12 = normalize(v1.add(v2, Vector3f()).div(2f))
        val v20 = normalize(v2.add(v0, Vector3f()).div(2f))

        val uv01 = normalize(Vector3f(uv0.add(uv1, Vector2f()).div(2f), 0f)).xy()
        val uv12 = normalize(Vector3f(uv1.add(uv2, Vector2f()).div(2f), 0f)).xy()
        val uv20 = normalize(Vector3f(uv2.add(uv0, Vector2f()).div(2f), 0f)).xy()

        val nuv0 = normalize(Vector3f(uv0, 0f)).xy()
        val nuv1 = normalize(Vector3f(uv01, 0f)).xy()
        val nuv2 = normalize(Vector3f(uv2, 0f)).xy()

        val tri0 = Triangle(normalize(v0.copy()), v01, v20, nuv0, uv01, uv20)
        val tri1 = Triangle(normalize(v1.copy()), v01, v12, nuv1, uv01, uv12)
        val tri2 = Triangle(normalize(v2.copy()), v12, v20, nuv2, uv12, uv20)
        val tri3 = Triangle(v01, v12, v20, uv01, uv12, uv20)

        return listOf(tri0, tri1, tri2, tri3)
    }

    override fun v0(): Vector3f = v0
    override fun v1(): Vector3f = v1
    override fun v2(): Vector3f = v2
    override fun uvs(): List<Vector2f> = listOf(uv0, uv1, uv2)
}