package net.wiredtomato.perception.systems.math

import org.joml.Vector2f

data class ScreenSpaceBorder(var topLeft: Vector2f, var bottomRight: Vector2f) {
    constructor(x0: Float, y0: Float, x1: Float, y1: Float): this(Vector2f(x0, y0), Vector2f(x1, y1))

    var x0
        inline get() = topLeft.x
        inline set(value) { topLeft.x = value }

    var y0
        inline get() = topLeft.y
        inline set(value) { topLeft.y = value }

    var x1
        inline get() = bottomRight.x
        inline set(value) { bottomRight.x = value }

    var y1
        inline get() = bottomRight.y
        inline set(value) { bottomRight.y = value }

    fun toTextureSpace() = TextureSpaceBorder(topLeft, bottomRight)
}