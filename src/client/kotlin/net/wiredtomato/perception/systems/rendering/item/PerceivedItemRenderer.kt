package net.wiredtomato.perception.systems.rendering.item

import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.util.Identifier
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.systems.rendering.VFX

interface PerceivedItemRenderer {
    fun renderItem(stack: MatrixStack, modelTransform: ModelTransformationMode, consumers: VertexConsumerProvider, vfxBuilder: VFX.WorldVFXBuilder, item: ItemStack, time: Float, delayRender: Boolean)
    fun id(): Identifier? = REGISTRY.getId(this)

    companion object {
        @JvmField
        val REG_KEY: RegistryKey<Registry<PerceivedItemRenderer>> = RegistryKey.ofRegistry(Perception.id("item_renderers"))
        @JvmField
        val REGISTRY: Registry<PerceivedItemRenderer> = FabricRegistryBuilder.createSimple(REG_KEY).buildAndRegister()

        fun init() {}
    }
}