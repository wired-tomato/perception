package net.wiredtomato.perception.systems.rendering.handling

import net.minecraft.client.render.ShaderProgram

fun interface ShaderUniformUpdater {
    fun updateUniforms(shader: ShaderProgram?)
}