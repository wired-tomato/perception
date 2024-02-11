package net.wiredtomato.perception.systems.rendering.shapes.geometry

import com.mojang.blaze3d.vertex.VertexFormat
import net.wiredtomato.perception.systems.rendering.shapes.Triangle
import org.joml.Vector2f
import org.joml.Vector3f

interface GeometricTriangle: DrawableGeometricShape {
    fun v0(): Vector3f
    fun v1(): Vector3f
    fun v2(): Vector3f

    override fun vertices(): List<Vector3f> = listOf(v0(), v1(), v2())
    override fun faces(): List<GeometricTriangle> = listOf(this)
    override fun isQuads() = false

    fun withUv(uv0: Vector2f, uv1: Vector2f, uv2: Vector2f) = Triangle(v0(), v1(), v2(), uv0, uv1, uv2)

    override fun drawMode(): VertexFormat.DrawMode = VertexFormat.DrawMode.TRIANGLES
}