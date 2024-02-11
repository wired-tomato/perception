package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjMaterialLibrary
import net.wiredtomato.perception.systems.rendering.shapes.Quad
import net.wiredtomato.perception.systems.rendering.shapes.Triangle
import net.wiredtomato.perception.systems.rendering.shapes.geometry.DrawableGeometricShape
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import org.joml.Vector2f
import org.joml.Vector3f

class GeometricObj(
    private val meshes: Map<String, ObjMesh>,
    private val mtllib: ObjMaterialLibrary?
): GeometricShape {

    override fun vertices() = meshes.map { it.value.vertices() }.reduce { a, b -> a + b }
    fun normals() = meshes.map { it.value.normals() }.reduce { a, b -> a + b }
    override fun uvs() = meshes.map { it.value.uvs() }.reduce { a, b -> a + b }
    override fun faces() = meshes.map { it.value.faces() }.reduce { a, b -> a + b }
    fun mtllib() = mtllib
    fun getMesh(name: String) = meshes[name]

    companion object {
        fun builder() = Builder()
    }

    class ObjMesh(
        val name: String,
        private val vertices: List<Vector3f>,
        private val normals: List<Vector3f>,
        private val uvs: List<Vector2f>,
        private val faces: List<DrawableGeometricShape>,
        private val shadingGroup: String,
        private val material: ObjMaterialLibrary.Material?
    ): DrawableGeometricShape {
        override fun draw(
            vertexConsumer: VertexConsumer,
            vertexPlacer: VertexPlacer,
            stack: MatrixStack,
            color: RGBA,
            light: Int
        ) {
            faces.forEach {
                it.draw(vertexConsumer, vertexPlacer, stack, color, light)
            }
        }

        override fun drawMode() = if (faces[0] is Triangle) VertexFormat.DrawMode.TRIANGLES else VertexFormat.DrawMode.QUADS

        override fun vertices() = vertices
        override fun uvs() = uvs
        override fun faces() = faces
        fun normals() = normals
        fun shadingGroup() = shadingGroup
    }

    class Builder {
        private val meshes = mutableMapOf<String, ObjMesh>()
        private var currentMesh: MeshBuilder? = null
        private var mtllib: ObjMaterialLibrary? = null

        fun newMesh(name: String?) = apply {
            if (currentMesh != null) currentMesh!!.build()
            currentMesh = MeshBuilder(name)
        }

        fun mesh() = currentMesh

        fun mtllib(mtllib: ObjMaterialLibrary) = apply { this.mtllib = mtllib }
        fun mtllib() = mtllib

        fun build() = GeometricObj(meshes, mtllib)

        inner class MeshBuilder(private var name: String?) {
            private val vertices: MutableList<Vector3f> = mutableListOf()
            private val normals: MutableList<Vector3f> = mutableListOf()
            private val uvs: MutableList<Vector2f> = mutableListOf()
            private val faces: MutableList<DrawableGeometricShape> = mutableListOf()
            private var shadingGroup: String = ""
            private var material: ObjMaterialLibrary.Material? = null

            fun name(name: String) = apply { this.name = name }
            fun name() = name
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
            fun shadingGroup(shadingGroup: String) = apply { this.shadingGroup = shadingGroup }
            fun shadingGroup() = shadingGroup
            fun material(material: ObjMaterialLibrary.Material) = apply { this.material = material }
            fun material() = material

            fun build() = apply {
                val mesh = ObjMesh(name!!, vertices, normals, uvs, faces, shadingGroup, material)
                meshes[name!!] = mesh
            }
        }
    }
}