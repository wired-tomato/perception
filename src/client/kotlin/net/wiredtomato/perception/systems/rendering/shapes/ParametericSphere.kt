package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.F_PI
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer
import net.wiredtomato.perception.systems.rendering.util.parametricSphere

class ParametericSphere(var radius: Float, var longs: Int, var lats: Int): Drawable {
    override fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int) {
        val last = stack.peek().model
        val startU = 0
        val startV = 0
        val endU = F_PI * 2
        val endV = F_PI
        val stepU = (endU - startU) / longs
        val stepV = (endV - startV) / lats
        for (i in 0..<longs) {
            for (j in 0..<lats) {
                val u = i * stepU + startU
                val v = j * stepV + startV
                val un = if (i + 1 == longs) endU else (i + 1) * stepU + startU
                val vn = if (j + 1 == lats) endV else (j + 1) * stepV + startU
                val p0 = parametricSphere(u, v, radius)
                val p1 = parametricSphere(u, vn, radius)
                val p2 = parametricSphere(un, v, radius)
                val p3 = parametricSphere(un, vn, radius)

                val texU = u / endU * radius
                val texV = v / endV * radius
                val texUN = un / endU * radius
                val texVN = vn / endV * radius
                vertexPlacer.place(vertexConsumer, last, p0.x, p0.y, p0.z, color.r, color.g, color.b, color.a, texU, texV, light)
                vertexPlacer.place(vertexConsumer, last, p2.x, p2.y, p2.z, color.r, color.g, color.b, color.a, texUN, texV, light)
                vertexPlacer.place(vertexConsumer, last, p1.x, p1.y, p1.z, color.r, color.g, color.b, color.a, texU, texVN, light)
                vertexPlacer.place(vertexConsumer, last, p3.x, p3.y, p3.z, color.r, color.g, color.b, color.a, texUN, texVN, light)
                vertexPlacer.place(vertexConsumer, last, p1.x, p1.y, p1.z, color.r, color.g, color.b, color.a, texU, texVN, light)
                vertexPlacer.place(vertexConsumer, last, p2.x, p2.y, p2.z, color.r, color.g, color.b, color.a, texUN, texV, light)
            }
        }
    }

    override fun drawMode(): VertexFormat.DrawMode = VertexFormat.DrawMode.TRIANGLES
}