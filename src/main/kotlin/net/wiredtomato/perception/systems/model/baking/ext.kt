package net.wiredtomato.perception.systems.model.baking

import net.minecraft.util.math.AffineTransformation
import net.minecraft.util.math.Direction
import net.wiredtomato.perception.systems.util.inlineCast
import org.joml.Matrix3f
import org.joml.Vector3f
import org.joml.Vector4f

interface AffineTransformationExtMedium {
    fun applyOrigin(origin: Vector3f): AffineTransformation
    fun getNormalMatrix(): Matrix3f
    fun transformPosition(position: Vector4f)
    fun rotateTransform(direction: Direction): Direction
    fun isIdentity(): Boolean
    fun transformNormal(normal: Vector3f) {
        normal.mul(getNormalMatrix())
        normal.normalize()
    }
    fun blockCenterToCorner() = applyOrigin(Vector3f(0.5f))
    fun blockCornerToCenter() = applyOrigin(Vector3f(-0.5f))
}

object AffineTransformationExt {
    val AffineTransformation.normalMatrix get() = inlineCast<AffineTransformationExtMedium>(this).getNormalMatrix()
    val AffineTransformation.isIdentity get() = inlineCast<AffineTransformationExtMedium>(this).isIdentity()

    fun AffineTransformation.applyOrigin(origin: Vector3f) = inlineCast<AffineTransformationExtMedium>(this).applyOrigin(origin)
    fun AffineTransformation.transformPosition(position: Vector4f) = inlineCast<AffineTransformationExtMedium>(this).transformPosition(position)
    fun AffineTransformation.rotateTransform(direction: Direction) = inlineCast<AffineTransformationExtMedium>(this).rotateTransform(direction)
    fun AffineTransformation.transformNormal(normal: Vector3f) = inlineCast<AffineTransformationExtMedium>(this).transformNormal(normal)
    fun AffineTransformation.blockCenterToCorner() = inlineCast<AffineTransformationExtMedium>(this).blockCenterToCorner()
    fun AffineTransformation.blockCornerToCenter() = inlineCast<AffineTransformationExtMedium>(this).blockCornerToCenter()
}
