package net.wiredtomato.perception.systems.rendering.shading

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.render.RenderPhase
import net.minecraft.client.render.ShaderProgram
import net.minecraft.client.texture.SpriteAtlasTexture
import net.wiredtomato.perception.systems.rendering.phase.PerceivedPhaseShader
import net.wiredtomato.perception.systems.util.KSupplier

open class PerceivedShaderHolder(val uniforms: List<String>) {
    @get:JvmName("_instance")
    @set:JvmName("_instance")
    private lateinit var instance: PerceivedShader
    val defaultUniforms = mutableListOf<UniformData>()
    val phase: RenderPhase.Shader = PerceivedPhaseShader(getInstance())

    fun setDefaults() {
        RenderSystem.setShaderTexture(1, SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE)
        defaultUniforms.forEach { it.setUniformValue(instance.getUniformOrDefault(it.name)) }
    }

    fun getInstance(): KSupplier<ShaderProgram> = { instance }
    fun setInstance(instance: PerceivedShader) {
        this.instance = instance
    }
}