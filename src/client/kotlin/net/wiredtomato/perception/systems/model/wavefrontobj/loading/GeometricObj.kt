package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.wiredtomato.perception.systems.rendering.shapes.Quad
import net.wiredtomato.perception.systems.rendering.shapes.Triangle
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape
import org.joml.Vector2f
import org.joml.Vector3f

class GeometricObj(
    private val vertices: List<Vector3f>,
    private val normals: List<Vector3f>,
    private val uvs: List<Vector2f>,
    private val faces: List<GeometricShape>,
    private val smoothShading: Boolean
): GeometricShape {

    override fun vertices() = vertices
    fun normals() = normals
    override fun uvs() = uvs
    override fun faces() = faces
    fun smoothShader() = smoothShading

    companion object {
        fun builder() = Builder()
    }

    class Builder {
        private val vertices: MutableList<Vector3f> = mutableListOf()
        private val normals: MutableList<Vector3f> = mutableListOf()
        private val uvs: MutableList<Vector2f> = mutableListOf()
        private val faces: MutableList<GeometricShape> = mutableListOf()
        private var smoothShading: Boolean = false

        fun vertex(vertex: Vector3f) = apply { vertices += vertex }
        fun vertices() = vertices
        fun normal(normal: Vector3f) = apply { normals += normal }
        fun normals() = normals
        fun uv(uv: Vector2f) = apply { uvs += uv }
        fun uvs() = uvs
        fun face(vertexIndices: List<Int>, uvIndices: List<Int>, normalIndices: List<Int>) = apply {
            if (vertexIndices.size == 3) {
                faces += if (uvIndices.isEmpty()) Triangle(vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]])
                else Triangle(
                    vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]],
                    uvs[uvIndices[0]],          uvs[uvIndices[1]],          uvs[uvIndices[2]]
                )
            } else if (vertexIndices.size == 4) {
                faces += if (uvIndices.isEmpty())
                    Quad(vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]], vertices[vertexIndices[3]])
                else Quad(
                    vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]], vertices[vertexIndices[3]],
                    uvs[uvIndices[0]], uvs[uvIndices[1]], uvs[uvIndices[2]], uvs[uvIndices[3]]
                )
            }
        }
        fun faces() = faces
        fun smoothShading(ss: Boolean) = apply { this.smoothShading = ss }
        fun smoothShading() = smoothShading

        fun build() = GeometricObj(vertices, normals, uvs, faces, smoothShading)
    }
}