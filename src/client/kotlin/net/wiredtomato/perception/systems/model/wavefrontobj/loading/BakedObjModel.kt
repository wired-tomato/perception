package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.minecraft.block.BlockState
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.BakedQuad
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.texture.Sprite
import net.minecraft.util.math.Direction
import net.minecraft.util.random.RandomGenerator
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape

class BakedObjModel(
    val shape: GeometricShape
): BakedModel {
    override fun getQuads(state: BlockState?, face: Direction?, random: RandomGenerator): MutableList<BakedQuad> {
        TODO("Not yet implemented")
    }

    override fun useAmbientOcclusion(): Boolean {
        TODO("Not yet implemented")
    }

    override fun hasDepth(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isSideLit(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isBuiltin(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getParticleSprite(): Sprite {
        TODO("Not yet implemented")
    }

    override fun getTransformation(): ModelTransformation {
        TODO("Not yet implemented")
    }

    override fun getOverrides(): ModelOverrideList {
        TODO("Not yet implemented")
    }

    override fun isVanillaAdapter() = false
}
