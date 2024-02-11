package net.wiredtomato.perception.systems.rendering.shapes.geometry

import org.joml.Vector2f
import org.joml.Vector3f

interface GeometricShape {
    fun vertices(): List<Vector3f>
    fun uvs(): List<Vector2f>
    fun faces(): List<GeometricShape>
    fun isQuads() = faces()[0] is GeometricQuad

    class ComplexMutable(
        val vertices: MutableList<Vector3f>,
        val uvs: MutableList<Vector2f>,
        val faces: MutableList<GeometricShape>
    ): GeometricShape {
        override fun vertices(): List<Vector3f> = vertices
        override fun uvs(): List<Vector2f> = uvs
        override fun faces(): List<GeometricShape> = faces

        fun toImmutable() = ComplexImmutable(vertices.toList(), uvs.toList(), faces.toList())
    }

    class ComplexImmutable(
        val vertices: List<Vector3f>,
        val uvs: List<Vector2f>,
        val faces: List<GeometricShape>
    ): GeometricShape {
        override fun vertices(): List<Vector3f> = vertices
        override fun uvs(): List<Vector2f> = uvs
        override fun faces(): List<GeometricShape> = faces

        fun toMutable() = ComplexMutable(vertices.toMutableList(), uvs.toMutableList(), faces.toMutableList())
    }

    companion object {
        fun mutable() = ComplexMutable(mutableListOf(), mutableListOf(), mutableListOf())
    }
}