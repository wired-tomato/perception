package net.wiredtomato.perception.systems.rendering.phase

import com.mojang.blaze3d.platform.GlStateManager.DestFactor
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor
import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.render.RenderPhase
import net.minecraft.client.render.ShaderProgram
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.util.KRunnable
import net.wiredtomato.perception.systems.util.KSupplier

open class PerceivedWriteMaskState(color: Boolean, depth: Boolean): RenderPhase.WriteMaskState(color, depth)
open class PerceivedLightmap(lightmap: Boolean): RenderPhase.Lightmap(lightmap)
open class PerceivedTransparency(name: String, enable: KRunnable, disable: KRunnable): RenderPhase.Transparency(name, enable, disable)
open class PerceivedTextureBase(apply: KRunnable, unapply: KRunnable): RenderPhase.TextureBase(apply, unapply)
open class PerceivedTexture(id: Identifier, blur: Boolean, mipmap: Boolean): RenderPhase.Texture(id, blur, mipmap)
open class PerceivedCull(culling: Boolean): RenderPhase.Cull(culling)
open class PerceivedPhaseShader(supplier: KSupplier<ShaderProgram>): RenderPhase.Shader(supplier)

val ADDITIVE_TRANSPARENCY = PerceivedTransparency("additive_transparency", {
    RenderSystem.depthMask(false)
    RenderSystem.enableBlend()
    RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE)
}) {
    RenderSystem.disableBlend()
    RenderSystem.defaultBlendFunc()
    RenderSystem.depthMask(true)
}

val NORMAL_TRANSPARENCY = PerceivedTransparency("normal_transparency", {
    RenderSystem.depthMask(false)
    RenderSystem.enableBlend()
    RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA)
}) {
    RenderSystem.disableBlend()
    RenderSystem.defaultBlendFunc()
    RenderSystem.depthMask(true)
}
