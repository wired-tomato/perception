package net.wiredtomato.perception.systems.model.wavefrontobj.baking

import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.ModelBakeSettings
import net.minecraft.client.render.model.ModelBaker
import net.minecraft.client.render.model.UnbakedModel
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.Sprite
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.util.KFunc

interface UnbakedGeometry<T: UnbakedGeometry<T>> {
    fun bake(
        ctx: JsonUnbakedModel,
        baker: ModelBaker,
        spriteSupplier: KFunc<Material, Sprite>,
        bakeSettings: ModelBakeSettings,
        overrides: ModelOverrideList,
        modelId: Identifier,
        hasDepth: Boolean
    ): BakedModel

    fun resolveParents(modelSupplier: KFunc<Identifier, UnbakedModel>, ctx: JsonUnbakedModel) {}
    fun configurableComponentNames(): Set<String> = setOf()
}