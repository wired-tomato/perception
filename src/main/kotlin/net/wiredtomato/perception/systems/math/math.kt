package net.wiredtomato.perception.systems.math

import net.minecraft.util.math.MathHelper
import net.wiredtomato.perception.Perception
import org.joml.Vector2f
import org.joml.Vector3f
import org.joml.Vector4f
import java.text.DecimalFormat
import kotlin.math.abs
import kotlin.math.atan
import kotlin.math.sqrt

const val FULL_BRIGHT = 15728880
const val F_PI = 3.1415927f

fun distSqr(vararg a: Float): Float {
    var d = 0.0f
    for (f in a) {
        d += f * f
    }
    return d
}

fun distance(vararg a: Float): Float {
    return sqrt(distSqr(*a))
}

fun screenSpaceQuadOffsets(start: Vector4f, end: Vector4f, width: Float): Vector2f {
    var x: Float = -start.x
    var y: Float = -start.y
    if (abs(start.z) > 0) {
        val ratio: Float = end.z / start.z
        x = end.x + x * ratio
        y = end.y + y * ratio
    } else if (abs(end.z) <= 0) {
        x += end.x
        y += end.y
    }
    if (start.z > 0) {
        x = -x
        y = -y
    }
    if (x * x + y * y > 0f) {
        val normalize: Float = width * 0.5f / distance(x, y)
        x *= normalize
        y *= normalize
    }
    return Vector2f(-y, x)
}

fun midpoint(a: Vector4f, b: Vector4f): Vector4f {
    return Vector4f((a.x + b.x) * 0.5f, (a.y + b.y) * 0.5f, (a.z + b.z) * 0.5f, (a.w + b.w) * 0.5f)
}
