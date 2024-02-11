package net.wiredtomato.perception.systems.rendering.handling

import com.mojang.blaze3d.vertex.BufferBuilder
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.rendering.shading.PerceivedShader
import net.wiredtomato.perception.systems.rendering.util.getShader
import kotlin.properties.Delegates

object RenderHandler {
    val EARLY_BUFFERS = mutableMapOf<RenderLayer, BufferBuilder>()
    val BUFFERS = mutableMapOf<RenderLayer, BufferBuilder>()
    val LATE_BUFFERS = mutableMapOf<RenderLayer, BufferBuilder>()
    val UPDATERS = mutableMapOf<RenderLayer, ShaderUniformUpdater>()
    var EARLY_DELAYED_RENDER by Delegates.notNull<VertexConsumerProvider.Immediate>()
    var DELAYED_RENDER by Delegates.notNull<VertexConsumerProvider.Immediate>()
    var LATE_DELAYED_RENDER by Delegates.notNull<VertexConsumerProvider.Immediate>()

    fun init() {
        EARLY_DELAYED_RENDER = VertexConsumerProvider.immediate(EARLY_BUFFERS, BufferBuilder(
            if (FabricLoader.getInstance().isModLoaded("sodium")) 262144 else 256
        ))

        DELAYED_RENDER = VertexConsumerProvider.immediate(BUFFERS, BufferBuilder(
            if (FabricLoader.getInstance().isModLoaded("sodium")) 2097152 else 256
        ))

        LATE_DELAYED_RENDER = VertexConsumerProvider.immediate(LATE_BUFFERS, BufferBuilder(
            if (FabricLoader.getInstance().isModLoaded("sodium")) 262144 else 256
        ))
    }

    fun draw(source: VertexConsumerProvider.Immediate, buffers: Map<RenderLayer, BufferBuilder>) {
        buffers.keys.forEach { layer ->
            val shader = getShader(layer)
            if (UPDATERS.containsKey(layer)) {
                val updater = UPDATERS[layer]!!
                updater.updateUniforms(shader)
            }
            source.draw(layer)
            if (shader is PerceivedShader) {
                shader.setDefaults()
            }
        }
        source.draw()
    }

    fun addLayer(layer: RenderLayer) {
        EARLY_BUFFERS[layer] = BufferBuilder(layer.expectedBufferSize)
        BUFFERS[layer] = BufferBuilder(layer.expectedBufferSize)
        LATE_BUFFERS[layer] = BufferBuilder(layer.expectedBufferSize)
    }
}