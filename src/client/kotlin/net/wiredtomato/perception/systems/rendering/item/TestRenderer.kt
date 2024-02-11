package net.wiredtomato.perception.systems.rendering.item

import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.VertexFormats
import net.minecraft.client.render.RenderPhase
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registry
import net.wiredtomato.perception.Perception
import net.wiredtomato.perception.Perception.id
import net.wiredtomato.perception.systems.rendering.VFX
import net.wiredtomato.perception.systems.rendering.layer.PerceivedRenderLayerProvider
import net.wiredtomato.perception.systems.rendering.shapes.IcoSphere
import org.joml.Vector3f

class TestRenderer: PerceivedItemRenderer {
    override fun renderItem(
        stack: MatrixStack,
        modelTransform: ModelTransformationMode,
        consumers: VertexConsumerProvider,
        vfxBuilder: VFX.WorldVFXBuilder,
        item: ItemStack,
        time: Float,
        delayRender: Boolean
    ) {
        val icoSphere = IcoSphere(Vector3f(0f, 0f, 0f), 1f, 1)
        vfxBuilder.renderShape(consumers.getBuffer(SOLIDS_LAYER(id("solids"))), stack, icoSphere)
    }

    companion object {
        val SOLIDS_LAYER = PerceivedRenderLayerProvider { id ->
            PerceivedRenderLayerProvider.createGenericRenderLayer(
                Perception.MOD_ID, "solids", VertexFormats.POSITION_COLOR_TEXTURE_LIGHT, VertexFormat.DrawMode.TRIANGLES,
                RenderPhase.POSITION_COLOR_TEXTURE_LIGHTMAP_SHADER, RenderPhase.NO_TRANSPARENCY, true, id
            )
        }

        fun init() {
            Registry.register(PerceivedItemRenderer.REGISTRY, id("test_renderer"), TestRenderer())
        }
    }
}