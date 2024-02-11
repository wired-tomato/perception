package net.wiredtomato.perception.systems.rendering

import com.mojang.blaze3d.vertex.VertexConsumer
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

data class Branch(
    val xp: Float,
    val xn: Float,
    val yp: Float,
    val yn: Float,
    val z: Float
) {
    constructor(pos: Vector4f, perp: Vector2f): this(pos.x + perp.x, pos.x - perp.x, pos.y + perp.y, pos.y - perp.y, pos.z)
    constructor(pos: Vector3f, perp: Vector2f): this(pos.x + perp.x, pos.x - perp.x, pos.y + perp.y, pos.y - perp.y, pos.z)

    fun renderStart(builder: VertexConsumer, placementSupplier: VFX.WorldVertexPlacementSupplier, u0: Float, v0: Float, u1: Float, v1: Float) {
        placementSupplier.placeVertex(builder, null, xp, yp, z, u0, v0)
        placementSupplier.placeVertex(builder, null, xn, yn, z, u1, v1)
    }

    fun renderEnd(builder: VertexConsumer, placementSupplier: VFX.WorldVertexPlacementSupplier, u0: Float, v0: Float, u1: Float, v1: Float) {
        placementSupplier.placeVertex(builder, null, xn, yn, z, u1, v1)
        placementSupplier.placeVertex(builder, null, xp, yp, z, u0, v1)
    }

    fun renderMid(builder: VertexConsumer, placementSupplier: VFX.WorldVertexPlacementSupplier, u0: Float, v0: Float, u1: Float, v1: Float) {
        renderEnd(builder, placementSupplier, u0, v0, u1, v1)
        renderStart(builder, placementSupplier, u0, v0, u1, v1)
    }
}