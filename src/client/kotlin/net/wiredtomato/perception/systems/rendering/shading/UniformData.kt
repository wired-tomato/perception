package net.wiredtomato.perception.systems.rendering.shading

import com.mojang.blaze3d.shader.Uniform

open class UniformData(val name: String, val type: Int) {
    open fun setUniformValue(uniform: Uniform) {}

    class FloatUniform(name: String, type: Int, val data: List<Float>): UniformData(name, type) {
        override fun setUniformValue(uniform: Uniform) {
            if (type <= 7) uniform.setForDataType(data[0], data[1], data[2], data[3])
            else uniform.setFloats(data.toFloatArray())
        }
    }

    class IntUniform(name: String, type: Int, val data: List<Int>): UniformData(name, type) {
        override fun setUniformValue(uniform: Uniform) {
            uniform.setForDataType(data[0], data[1], data[2], data[3])
        }
    }
}