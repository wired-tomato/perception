package net.wiredtomato.perception.systems.rendering.layer

import com.mojang.blaze3d.vertex.VertexFormat
import com.mojang.blaze3d.vertex.VertexFormat.DrawMode
import com.mojang.blaze3d.vertex.VertexFormats
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderPhase.*
import net.minecraft.util.Identifier
import net.minecraft.util.Util
import net.wiredtomato.perception.systems.rendering.handling.RenderHandler
import net.wiredtomato.perception.systems.rendering.phase.*
import net.wiredtomato.perception.systems.util.KFunc

class PerceivedRenderLayerProvider(private val func: KFunc<Identifier, RenderLayer>) {
    private val memorizedFunction = Util.memoize(func)

    operator fun invoke(identifier: Identifier): RenderLayer {
        return func(identifier)
    }

    fun invokeCached(identifier: Identifier): RenderLayer {
        return memorizedFunction.apply(identifier)
    }

    companion object {
        fun createGenericRenderLayer(modId: String, name: String, format: VertexFormat, drawMode: DrawMode, shader: Shader, transparency: Transparency, cull: Boolean) =
            createGenericRenderLayer("$modId:$name", format, drawMode, shader, transparency, cull, NO_TEXTURE)

        fun createGenericRenderLayer(modId: String, name: String, format: VertexFormat, drawMode: DrawMode, shader: Shader, transparency: Transparency, cull: Boolean, texture: Identifier) =
            createGenericRenderLayer("$modId:$name", format, drawMode, shader, transparency, cull, PerceivedTexture(texture,
                blur = false,
                mipmap = false
            ))

        fun createGenericRenderLayer(modId: String, name: String, format: VertexFormat, drawMode: DrawMode, shader: Shader, transparency: Transparency, cull: Boolean, textureBase: TextureBase) =
            createGenericRenderLayer("$modId:$name", format, drawMode, shader, transparency, cull, textureBase)

        fun createGenericRenderLayer(name: String, format: VertexFormat, drawMode: DrawMode, shader: Shader, transparency: Transparency, cull: Boolean, textureBase: TextureBase): RenderLayer {
            val layer = RenderLayer.of(
                name, format, drawMode,
                if (FabricLoader.getInstance().isModLoaded("sodium")) 2097152 else 256,
                false, false,
                RenderLayer.MultiPhaseParameters.builder()
                    .shader(shader)
                    .writeMaskState(PerceivedWriteMaskState(color = true, depth = true))
                    .lightmap(PerceivedLightmap(false))
                    .transparency(transparency)
                    .texture(textureBase)
                    .cull(PerceivedCull(cull))
                    .build(true)
            )
            RenderHandler.addLayer(layer)
            return layer
        }
    }
}