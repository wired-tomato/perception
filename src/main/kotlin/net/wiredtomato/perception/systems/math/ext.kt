package net.wiredtomato.perception.systems.math

import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f

var Vector2f.u
    inline get() = x
    inline set(value) { x = value }

var Vector2f.v
    inline get() = y
    inline set(value) { y = value }

fun Vector3f.copy(): Vector3f {
    return Vector3f(this)
}