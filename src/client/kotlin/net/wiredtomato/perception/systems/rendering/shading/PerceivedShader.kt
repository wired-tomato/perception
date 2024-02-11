package net.wiredtomato.perception.systems.rendering.shading

import com.google.gson.JsonElement
import com.mojang.blaze3d.shader.GlUniform
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.gl.ShaderParseException
import net.minecraft.client.render.ShaderProgram
import net.minecraft.resource.ResourceFactory
import net.minecraft.util.Identifier
import net.minecraft.util.JsonHelper
import java.io.IOException
import java.util.*
import kotlin.math.max

open class PerceivedShader(factory: ResourceFactory, name: String, format: VertexFormat): ShaderProgram(factory, name, format) {
    open fun setDefaults() = getHolder()?.setDefaults()
    open fun getHolder(): PerceivedShaderHolder? = null

    override fun addUniform(json: JsonElement?) {
        if (getHolder()?.uniforms?.isEmpty() == true) {
            super.addUniform(json)
            return
        }

        val obj = JsonHelper.asObject(json, "uniform")
        val name = JsonHelper.getString(obj, "name")
        val type = GlUniform.getTypeIndex(JsonHelper.getString(obj, "type"))
        val count = JsonHelper.getInt(obj, "count")
        val data = MutableList(max(count, 16)) { 0f }
        val array = JsonHelper.getArray(obj, "values")
        if (array.size() != count && array.size() > 1) {
            throw ShaderParseException("Invalid number of values, $count were expected, only ${array.size()} were received")
        }

        var lastIdx = 0
        array.forEachIndexed { index, element ->
            val res = runCatching {
                data[index] = JsonHelper.asFloat(element, "value")
            }

            if (res.isFailure) {
                val errChain = ShaderParseException.wrap(res.exceptionOrNull()!! as Exception)
                errChain.addFaultyElement("values[$index]")
                throw errChain
            }

            ++lastIdx
        }

        if (count > 1 && array.size() == 1) {
            while (lastIdx < count) {
                data[lastIdx] = data[0]
                ++lastIdx
            }
        }

        val l = if (count in 2..4 && type < 8) count - 1 else 0
        val uniform = GlUniform(name, type + l, count, this)
        if (type <= 3) {
            uniform.setForDataType(data[0].toInt(), data[1].toInt(), data[2].toInt(), data[3].toInt())
            if (getHolder()?.uniforms?.contains(name) == true) {
                getHolder()?.defaultUniforms?.add(UniformData.IntUniform(name, type, data.map { it.toInt() }))
            }
        } else if (type <= 7) {
            uniform.setForDataType(data[0], data[1], data[2], data[3])
        } else {
            uniform.setFloats(Arrays.copyOfRange(data.toFloatArray(), 0, count))
        }
        if (type > 3 && getHolder()?.uniforms?.contains(name) == true) {
            getHolder()?.defaultUniforms?.add(UniformData.FloatUniform(name, type, data))
        }

        this.uniforms.add(uniform)
    }

    companion object {
        @Throws(IOException::class)
        fun createShader(holder: PerceivedShaderHolder, factory: ResourceFactory, id: Identifier, format: VertexFormat): PerceivedShader {
            return object: PerceivedShader(factory, id.toString(), format) {
                override fun getHolder(): PerceivedShaderHolder = holder
            }
        }
    }
}