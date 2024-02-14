package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder
import net.minecraft.client.render.model.ModelBakeSettings
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.Sprite
import net.minecraft.util.math.AffineTransformation
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.calculateNormal
import net.wiredtomato.perception.systems.math.defaultUvCoords
import net.wiredtomato.perception.systems.model.UnbakedGeometryHelper
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExt.isIdentity
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjMaterialLibrary
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjQuad
import net.wiredtomato.perception.systems.model.wavefrontobj.ObjTri
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.JsonUnbakedModelExt.rootTransform
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.exceptions.ObjParseException
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape
import net.wiredtomato.perception.systems.rendering.shapes.geometry.NormalGeometricShape
import net.wiredtomato.perception.systems.util.KFunc
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
    fun meshes() = meshes.values.toList()

    companion object {
        fun builder() = Builder()
    }

    fun bakeMeshes(
        owner: JsonUnbakedModel?,
        builder: MeshBuilder,
        spriteSupplier: KFunc<Material, Sprite>,
        bakeSettings: ModelBakeSettings,
        quadBaker: (MeshBuilder, List<Vector3f>, List<Vector2f>, List<Vector3f>, Int, RGBA, Sprite, AffineTransformation) -> Unit
    ) {
        meshes.forEach { (_, mesh) ->
            mesh.bake(owner, builder, spriteSupplier, bakeSettings, quadBaker)
        }
    }

    class ObjMesh(
        val name: String,
        private val vertices: List<Vector3f>,
        private val normals: List<Vector3f>,
        private val uvs: List<Vector2f>,
        private val faces: List<NormalGeometricShape>,
        private val shadingGroup: String,
        private val material: ObjMaterialLibrary.Material?
    ): NormalGeometricShape {
        override fun vertices() = vertices
        override fun uvs() = uvs
        override fun faces() = faces
        override fun normals() = normals
        fun shadingGroup() = shadingGroup
        fun material() = material

        fun bake(
            owner: JsonUnbakedModel?,
            builder: MeshBuilder,
            spriteSupplier: KFunc<Material, Sprite>,
            bakeSettings: ModelBakeSettings,
            quadBaker: (MeshBuilder, List<Vector3f>, List<Vector2f>, List<Vector3f>, Int, RGBA, Sprite, AffineTransformation) -> Unit
        ) {
            val material = material() ?: return

            val texture = spriteSupplier(UnbakedGeometryHelper.resolveDirtyMaterial(material.diffuseColorMap, owner))
            val rootTransform = owner?.rootTransform ?: AffineTransformation.identity()
            val transform = if (rootTransform.isIdentity) bakeSettings.rotation else bakeSettings.rotation.multiply(rootTransform)
            for (face in faces()) {
                if (face !is ObjQuad) throw IllegalStateException("attempted to bake a model that contains tris")
                quadBaker(builder, face.vertices(), face.uvs(), face.normals(), material.diffuseTintIndex, material.ambientColor, texture, transform)
            }
        }
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
            private val faces: MutableList<NormalGeometricShape> = mutableListOf()
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
                val face = if (vertexIndices.size == 4) {
                    if (uvIndices.isNotEmpty()) {
                        if (normalIndices.isNotEmpty()) {
                            ObjQuad(
                                vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]], vertices[vertexIndices[3]],
                                uvs[uvIndices[0]],          uvs[uvIndices[1]],          uvs[uvIndices[2]],          uvs[uvIndices[3]],
                                normals[normalIndices[0]],  normals[normalIndices[1]],  normals[normalIndices[2]],  normals[normalIndices[3]]
                            )
                        } else {
                            val v1 = vertices[vertexIndices[0]]
                            val v2 = vertices[vertexIndices[1]]
                            val v3 = vertices[vertexIndices[2]]
                            val v4 = vertices[vertexIndices[3]]

                            ObjQuad(
                                v1,                          v2,                          v3,                          v4,
                                uvs[uvIndices[0]],           uvs[uvIndices[1]],           uvs[uvIndices[2]],           uvs[uvIndices[3]],
                                calculateNormal(v1, v2, v4), calculateNormal(v2, v3, v1), calculateNormal(v3, v2, v4), calculateNormal(v4, v1, v3)
                            )
                        }
                    } else {
                        val v1 = vertices[vertexIndices[0]]
                        val v2 = vertices[vertexIndices[1]]
                        val v3 = vertices[vertexIndices[2]]
                        val v4 = vertices[vertexIndices[3]]

                        ObjQuad(
                            v1,                          v2,                          v3,                          v4,
                            defaultUvCoords[0],          defaultUvCoords[1],          defaultUvCoords[2],          defaultUvCoords[3],
                            calculateNormal(v1, v2, v4), calculateNormal(v2, v3, v1), calculateNormal(v3, v2, v4), calculateNormal(v4, v1, v3)
                        )
                    }
                } else if (vertexIndices.size == 3) {
                    if (uvIndices.isNotEmpty()) {
                        if (normalIndices.isNotEmpty()) {
                            ObjQuad(
                                vertices[vertexIndices[0]], vertices[vertexIndices[1]], vertices[vertexIndices[2]], vertices[vertexIndices[3]],
                                uvs[uvIndices[0]],          uvs[uvIndices[1]],          uvs[uvIndices[2]],          uvs[uvIndices[3]],
                                normals[normalIndices[0]],  normals[normalIndices[1]],  normals[normalIndices[2]],  normals[normalIndices[3]]
                            )
                        } else {
                            val v1 = vertices[vertexIndices[0]]
                            val v2 = vertices[vertexIndices[1]]
                            val v3 = vertices[vertexIndices[2]]

                            ObjTri(
                                v1,                          v2,                          v3,
                                uvs[uvIndices[0]],          uvs[uvIndices[1]],          uvs[uvIndices[2]],
                                calculateNormal(v1, v2, v3), calculateNormal(v2, v1, v3), calculateNormal(v3, v1, v2)
                            )
                        }
                    } else {
                        val v1 = vertices[vertexIndices[0]]
                        val v2 = vertices[vertexIndices[1]]
                        val v3 = vertices[vertexIndices[2]]

                        ObjTri(
                            v1,                          v2,                          v3,
                            defaultUvCoords[0],          Vector2f(0.5f, 1f),    defaultUvCoords[3],
                            calculateNormal(v1, v2, v3), calculateNormal(v2, v1, v3), calculateNormal(v3, v1, v2)
                        )
                    }
                } else {
                    throw ObjParseException("Attempted to parse a vertex that was neither a tri nor quad")
                }


                faces += face
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
