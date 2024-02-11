package net.wiredtomato.perception.systems.math

import com.mojang.blaze3d.systems.RenderSystem
import net.minecraft.client.MinecraftClient
import net.minecraft.client.util.math.MatrixStack
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

fun worldPosToTexCoord(worldPos: Vector3f, stack: MatrixStack): Vector2f {
    val viewMat = stack.peek().model
    val projMat = RenderSystem.getProjectionMatrix()

    val localPos = worldPos.copy()
    localPos.sub(MinecraftClient.getInstance().gameRenderer.camera.pos.toVector3f())

    val pos = Vector4f(localPos, 1.0f)
    pos.mulTranspose(viewMat)
    pos.mulTranspose(projMat)
    pos.x /= pos.w
    pos.y /= pos.w
    pos.z /= pos.w
    pos.w = 1f

    return Vector2f((pos.x+1)/2, (pos.y+1)/2)
}
