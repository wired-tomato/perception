package net.wiredtomato.perception.systems.rendering.shapes

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.blaze3d.vertex.VertexFormat.DrawMode
import net.minecraft.client.util.math.MatrixStack
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.util.VertexPlacer

interface Drawable {
    fun draw(vertexConsumer: VertexConsumer, vertexPlacer: VertexPlacer, stack: MatrixStack, color: RGBA, light: Int)

    fun drawMode(): DrawMode
}