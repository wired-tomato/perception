package net.wiredtomato.perception.mixin;

import net.minecraft.util.math.AffineTransformation;
import net.minecraft.util.math.Direction;
import net.wiredtomato.perception.systems.model.baking.AffineTransformationExtMedium;
import net.wiredtomato.perception.systems.util.UtilKt;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AffineTransformation.class)
public abstract class AffineTransformationMixin implements AffineTransformationExtMedium {
    @Shadow @Final private Matrix4f matrix;

    @Shadow public abstract Matrix4f getMatrix();

    @Unique
    private Matrix3f normalTransform = null;

    @Override
    public AffineTransformation applyOrigin(@NotNull Vector3f origin) {
        if (isIdentity()) return AffineTransformation.identity();
        var ret = getMatrix();
        var tmp = new Matrix4f().translation(origin);
        tmp.mul(ret, ret);
        tmp.translation(origin.negate(new Vector3f()));
        ret.mul(tmp);
        return new AffineTransformation(ret);
    }

    @NotNull
    @Override
    public Matrix3f getNormalMatrix() {
        perception$checkNormalTransform();
        return normalTransform;
    }

    @Override
    public void transformPosition(@NotNull Vector4f position) {
        position.mul(getMatrix());
    }

    @NotNull
    @Override
    public Direction rotateTransform(@NotNull Direction direction) {
        return Direction.transform(getMatrix(), direction);
    }

    @Override
    public boolean isIdentity() {
        return UtilKt.<AffineTransformation>uncheckedCast(this).equals(AffineTransformation.identity());
    }

    @Unique
    private void perception$checkNormalTransform() {
        if (normalTransform == null) {
            normalTransform = new Matrix3f(matrix);
            normalTransform.invert();
            normalTransform.transpose();
        }
    }
}
