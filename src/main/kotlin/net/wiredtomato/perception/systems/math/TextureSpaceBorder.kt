package net.wiredtomato.perception.systems.math

import org.joml.Vector2f

data class TextureSpaceBorder(var topLeft: Vector2f, var bottomRight: Vector2f) {
    constructor(u0: Float, v0: Float, u1: Float, v1: Float): this(Vector2f(u0, v0), Vector2f(u1, v1))

    var u0
        inline get() = topLeft.x
        inline set(value) { topLeft.x = value }

    var v0
        inline get() = topLeft.y
        inline set(value) { topLeft.y = value }

    var u1
        inline get() = bottomRight.x
        inline set(value) { bottomRight.x = value }

    var v1
        inline get() = bottomRight.y
        inline set(value) { bottomRight.y = value }

    fun toScreenSpace() = ScreenSpaceBorder(topLeft, bottomRight)
}