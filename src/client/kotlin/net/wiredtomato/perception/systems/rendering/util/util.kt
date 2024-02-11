package net.wiredtomato.perception.systems.rendering.util

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.ShaderProgram
import net.minecraft.util.math.MathHelper.cos
import net.minecraft.util.math.MathHelper.sin
import net.wiredtomato.perception.systems.util.KFunc
import org.joml.Matrix4f
import org.joml.Vector3f

fun vertexPos(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float) {
    consumer.vertex(last, x, y, z).next()
}

fun vertexPosUV(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, u: Float, v: Float) {
    consumer.vertex(last, x, y, z).uv(u, v).next()
}

fun vertexPosUVLight(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, u: Float, v: Float, light: Int) {
    consumer.vertex(last, x, y, z).uv(u, v).light(light).next()
}

fun vertexPosColor(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, r: Float, g: Float, b: Float, a: Float) {
    consumer.vertex(last, x, y, z).color(r, g,  b, a).next()
}

fun vertexPosColorUV(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, r: Float, g: Float, b: Float, a: Float, u: Float, v: Float) {
    consumer.vertex(last, x, y, z).color(r, g, b, a).uv(u, v).next()
}

fun vertexPosColorUVLight(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, r: Float, g: Float, b: Float, a: Float, u: Float, v: Float, light: Int) {
    consumer.vertex(last, x, y, z).color(r, g, b, a).uv(u, v).light(light).next()
}

fun parametricSphere(u: Float, v: Float, radius: Float): Vector3f {
    return Vector3f(cos(u) * sin(v) * radius, cos(v) * radius, sin(u) * sin(v) * radius)
}

fun getShader(layer: RenderLayer): ShaderProgram? {
    if (layer is RenderLayer.MultiPhase) {
        val shader = layer.phases.shader.supplier
        if (shader.isPresent) {
            return shader.get().get()
        }
    }
    return null
}

fun interface VertexPlacer {
    fun place(consumer: VertexConsumer, last: Matrix4f, x: Float, y: Float, z: Float, r: Float, g: Float, b: Float, a: Float, u: Float, v: Float, light: Int)
}

fun normalizeFromCircleCenter(center: Vector3f, radius: Float): KFunc<Vector3f, Vector3f> {
    return {
        val delta = it.sub(center, Vector3f()).mul(radius / center.distance(it))
        Vector3f(center.x + delta.x, center.y + delta.y, center.z + delta.z)
    }
}
