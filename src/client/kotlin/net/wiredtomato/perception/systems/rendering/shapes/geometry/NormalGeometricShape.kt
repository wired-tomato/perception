package net.wiredtomato.perception.systems.rendering.shapes.geometry

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import org.joml.Vector2f
import org.joml.Vector3f

interface NormalGeometricShape: GeometricShape {
    fun normals(): List<Vector3f>

    class ComplexMutable(
        val vertices: MutableList<Vector3f>,
        val uvs: MutableList<Vector2f>,
        val normals: MutableList<Vector3f>,
        val faces: MutableList<NormalGeometricShape>
    ): NormalGeometricShape {

        override fun vertices(): List<Vector3f> = vertices

        override fun uvs(): List<Vector2f> = uvs
        override fun normals(): List<Vector3f> = normals
        override fun faces(): List<NormalGeometricShape> = faces

        fun toImmutable() = ComplexImmutable(vertices.toList(), uvs.toList(), normals.toList(), faces.toList())
    }

    class ComplexImmutable(
        val vertices: List<Vector3f>,
        val uvs: List<Vector2f>,
        val normals: List<Vector3f>,
        val faces: List<NormalGeometricShape>
    ): NormalGeometricShape {

        override fun vertices(): List<Vector3f> = vertices
        override fun uvs(): List<Vector2f> = uvs
        override fun normals(): List<Vector3f> = normals
        override fun faces(): List<NormalGeometricShape> = faces

        fun toMutable() = ComplexMutable(vertices.toMutableList(), uvs.toMutableList(), normals.toMutableList(), faces.toMutableList())
    }

    companion object {
        fun mutable() = ComplexMutable(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf())
    }
}