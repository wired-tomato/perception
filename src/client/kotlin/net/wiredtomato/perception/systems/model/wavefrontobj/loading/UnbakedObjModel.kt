package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.fabricmc.fabric.api.renderer.v1.Renderer
import net.fabricmc.fabric.api.renderer.v1.RendererAccess
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial
import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder
import net.fabricmc.fabric.api.renderer.v1.mesh.MutableQuadView
import net.minecraft.client.render.LightmapTextureManager
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.ModelBakeSettings
import net.minecraft.client.render.model.ModelBaker
import net.minecraft.client.render.model.UnbakedModel
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.MissingSprite
import net.minecraft.client.texture.Sprite
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.util.Identifier
import net.minecraft.util.math.AffineTransformation
import net.minecraft.util.math.Direction
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.approximatelyEquals
import net.wiredtomato.perception.systems.math.swizzling.zyz
import net.wiredtomato.perception.systems.math.vector.u
import net.wiredtomato.perception.systems.math.vector.v
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExt.blockCenterToCorner
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExt.isIdentity
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExt.transformNormal
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExt.transformPosition
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.UnbakedGeometry
import net.wiredtomato.perception.systems.util.KFunc
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import java.util.function.Function

class UnbakedObjModel(
    val settings: ModelSettings,
    val geometricObj: GeometricObj,
): UnbakedGeometry<UnbakedObjModel>, UnbakedModel {
    override fun bake(
        ctx: JsonUnbakedModel,
        baker: ModelBaker,
        spriteSupplier: KFunc<Material, Sprite>,
        bakeSettings: ModelBakeSettings,
        overrides: ModelOverrideList,
        modelId: Identifier,
        hasDepth: Boolean,
    ): BakedModel {
        val meshes = bakeMeshes(ctx, baker, spriteSupplier, bakeSettings)
        val particle = spriteSupplier(ctx.resolveSprite("particle"))
        return BakedObjModel(
            meshes,
            ctx.useAmbientOcclusion(),
            hasDepth,
            ctx.guiLight.isSide,
            false,
            ctx.transformations,
            overrides,
            particle
        )
    }

    override fun bake(
        modelBaker: ModelBaker,
        spriteSupplier: Function<Material, Sprite>,
        rotationContainer: ModelBakeSettings,
        modelId: Identifier?,
    ): BakedModel {
        val meshes = bakeMeshes(null, modelBaker, spriteSupplier::apply, rotationContainer)
        val particle = spriteSupplier.apply(Material(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, MissingSprite.getMissingSpriteId()))
        return BakedObjModel(
            meshes,
            ambientOcclusion = false,
            hasDepth = false,
            sideLit = false,
            customRenderer = false,
            transform = ModelTransformation.NONE,
            overrides = ModelOverrideList.EMPTY,
            particle = particle
        )
    }

    fun bakeMeshes(owner: JsonUnbakedModel?, baker: ModelBaker, spriteSupplier: KFunc<Material, Sprite>, bakeSettings: ModelBakeSettings): List<Mesh> {
        val meshes = geometricObj.meshes().map { mesh ->
            val meshBuilder = renderer!!.meshBuilder()
            mesh.bake(owner, meshBuilder, spriteSupplier, bakeSettings, this::bakeQuad)
            meshBuilder.build()
        }

        return meshes
    }

    fun bakeQuad(
        builder: MeshBuilder,
        vertices: List<Vector3f>,
        uvs: List<Vector2f>,
        normals: List<Vector3f>,
        tint: Int,
        ambientColor: RGBA,
        texture: Sprite,
        transform: AffineTransformation,
    ) {
        val emitter = builder.emitter
        emitter.spriteBake(texture, MutableQuadView.BAKE_ROTATE_NONE)
        emitter.colorIndex(tint)

        var lightUv = 0
        if (settings.emissiveAmbient) {
            val fakeLight = (ambientColor.sumRGB() * 15 / 3).toInt()
            lightUv = LightmapTextureManager.pack(fakeLight, fakeLight)
            emitter.material(if (fakeLight == 0 && settings.shadeQuads) defaultMtl else diffuseMtl)
        } else emitter.material(if (settings.shadeQuads) defaultMtl else diffuseMtl)

        val hasTransform = !transform.isIdentity
        val transformation = if (hasTransform) transform.blockCenterToCorner() else transform
        for (i in 0..3) {
            val vertex = Vector4f(vertices[i], 1f)
            val uv = uvs[i]
            val normal = normals[i]
            if (hasTransform) {
                transformation.transformPosition(vertex)
                transformation.transformNormal(normal)
            }
            emitter.pos(i, vertex.zyz())
            emitter.uv(i,
                texture.getFrameU(uv.u * 16),
                texture.getFrameV((if (settings.flipV) 1 - uv.v else uv.v) * 16)
            )
            emitter.lightmap(i, lightUv)
            emitter.normal(i, normal)
            if (i == 0) emitter.nominalFace(Direction.getFacing(normal.x, normal.y, normal.z))
        }

        val cull =
            if (settings.automaticCulling) {
                0f.approximatelyEquals(0f)
                if (
                    vertices[0].x.approximatelyEquals(0f) &&
                    vertices[1].x.approximatelyEquals(0f) &&
                    vertices[2].x.approximatelyEquals(0f) &&
                    vertices[3].x.approximatelyEquals(0f) &&
                    normals[0].x < 0
                ) {
                    Direction.WEST
                } else if (
                    vertices[0].x.approximatelyEquals(1f) &&
                    vertices[1].x.approximatelyEquals(1f) &&
                    vertices[2].x.approximatelyEquals(1f) &&
                    vertices[3].x.approximatelyEquals(1f) &&
                    normals[0].x > 0
                ) {
                    Direction.EAST
                } else if (
                    vertices[0].z.approximatelyEquals(0f) &&
                    vertices[1].z.approximatelyEquals(0f) &&
                    vertices[2].z.approximatelyEquals(0f) &&
                    vertices[3].z.approximatelyEquals(0f) &&
                    normals[0].z < 0
                ) {
                    Direction.NORTH
                } else if (
                    vertices[0].z.approximatelyEquals(1f) &&
                    vertices[1].z.approximatelyEquals(1f) &&
                    vertices[2].z.approximatelyEquals(1f) &&
                    vertices[3].z.approximatelyEquals(1f) &&
                    normals[0].z > 0
                ) {
                    Direction.SOUTH
                } else if (
                    vertices[0].y.approximatelyEquals(0f) &&
                    vertices[1].y.approximatelyEquals(0f) &&
                    vertices[2].y.approximatelyEquals(0f) &&
                    vertices[3].y.approximatelyEquals(0f) &&
                    normals[0].y < 0
                ) {
                    Direction.DOWN
                } else if (
                    vertices[0].y.approximatelyEquals(1f) &&
                    vertices[1].y.approximatelyEquals(1f) &&
                    vertices[2].y.approximatelyEquals(1f) &&
                    vertices[3].y.approximatelyEquals(1f) &&
                    normals[0].y > 0
                ) {
                    Direction.UP
                } else null
            } else null

        emitter.cullFace(cull)
        emitter.emit()
    }

    override fun getModelDependencies(): MutableCollection<Identifier> = mutableListOf()
    override fun resolveParents(models: Function<Identifier, UnbakedModel>?) { }

    companion object {
        val enabled: Boolean
        val renderer: Renderer?
        val diffuseMtl: RenderMaterial?
        val defaultMtl: RenderMaterial?

        init {
            renderer = RendererAccess.INSTANCE.renderer
            enabled = RendererAccess.INSTANCE.hasRenderer()
            if (enabled) {
                diffuseMtl = renderer!!.materialFinder().disableDiffuse(true).find()
                defaultMtl = renderer.materialById(RenderMaterial.MATERIAL_STANDARD)!!
            } else {
                Perception.logger.error("Fabric API unavailable if you have Sodium installed, install Indium")
                diffuseMtl = null
                defaultMtl = null
            }
        }
    }
}