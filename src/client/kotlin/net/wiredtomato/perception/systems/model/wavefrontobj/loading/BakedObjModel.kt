package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.fabricmc.fabric.api.renderer.v1.mesh.Mesh
import net.fabricmc.fabric.api.renderer.v1.model.SpriteFinder
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext
import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.ingame.InventoryScreen
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.BakedQuad
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.texture.Sprite
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.item.ItemStack
import net.minecraft.screen.PlayerScreenHandler
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.minecraft.world.BlockRenderView
import java.util.function.Supplier

class BakedObjModel(
    val meshes: List<Mesh>,
    val ambientOcclusion: Boolean,
    val hasDepth: Boolean,
    val sideLit: Boolean,
    val customRenderer: Boolean,
    val transform: ModelTransformation,
    @get:JvmName("_get-overridesc")
    val overrides: ModelOverrideList,
    val particle: Sprite
): BakedModel {
    override fun emitBlockQuads(
        blockView: BlockRenderView,
        state: BlockState,
        pos: BlockPos,
        randomSupplier: Supplier<RandomGenerator>,
        context: RenderContext
    ) {
        meshes.forEach { it.outputTo(context.emitter) }
    }

    override fun emitItemQuads(stack: ItemStack, randomSupplier: Supplier<RandomGenerator>, context: RenderContext) {
        meshes.forEach { it.outputTo(context.emitter) }
    }

    override fun getQuads(state: BlockState?, face: Direction?, random: RandomGenerator): MutableList<BakedQuad> {
        val atlas = MinecraftClient.getInstance().textureManager.getTexture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE)
        if (atlas !is SpriteAtlasTexture) return mutableListOf()

        val quads = mutableListOf<BakedQuad>()
        val finder = SpriteFinder.get(atlas)
        meshes.forEach { mesh ->
            mesh.forEach {
                val sprite = finder.find(it)
                quads += it.toBakedQuad(sprite)
            }
        }
        return quads
    }

    override fun useAmbientOcclusion() = ambientOcclusion
    override fun hasDepth() = hasDepth
    override fun isSideLit() = sideLit
    override fun isBuiltin() = customRenderer
    override fun getParticleSprite() = particle
    override fun getTransformation() = transform
    override fun getOverrides() = overrides
    override fun isVanillaAdapter() = false
}
