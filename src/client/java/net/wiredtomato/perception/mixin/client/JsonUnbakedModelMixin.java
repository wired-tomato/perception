package net.wiredtomato.perception.mixin.client;

import kotlin.jvm.functions.Function1;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.ModelBakeSettings;
import net.minecraft.client.render.model.ModelBaker;
import net.minecraft.client.render.model.UnbakedModel;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.render.model.json.ModelOverride;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.resource.Material;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.AffineTransformation;
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.JsonUnbakedModelExt;
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.JsonUnbakedModelExtMedium;
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.VisibilityData;
import net.wiredtomato.perception.systems.model.wavefrontobj.baking.UnbakedGeometry;
import net.wiredtomato.perception.systems.util.UtilKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.Function;

@Mixin(JsonUnbakedModel.class)
public class JsonUnbakedModelMixin implements JsonUnbakedModelExtMedium {
    @Shadow @Final private List<ModelOverride> overrides;
    @Shadow @Nullable public JsonUnbakedModel parent;
    @Unique
    private UnbakedGeometry<?> customModel;
    @Unique @Nullable
    private AffineTransformation rootTransform;
    @Unique
    public final VisibilityData visibilityData = new VisibilityData();

    @Inject(
            method = "bake(Lnet/minecraft/client/render/model/ModelBaker;Lnet/minecraft/client/render/model/json/JsonUnbakedModel;Ljava/util/function/Function;Lnet/minecraft/client/render/model/ModelBakeSettings;Lnet/minecraft/util/Identifier;Z)Lnet/minecraft/client/render/model/BakedModel;",
            at = @At("HEAD"),
            cancellable = true
    )
    public void handleCustomGeometry(
            ModelBaker modelBaker,
            JsonUnbakedModel unbakedModel,
            Function<Material, Sprite> spriteSupplier,
            ModelBakeSettings bakeSettings,
            Identifier id,
            boolean hasDepth,
            CallbackInfoReturnable<BakedModel> cir
    ) {
        var geometry = getCustomGeometry();
        if (geometry != null) {
            var overrides = getOverrides(modelBaker, unbakedModel, spriteSupplier::apply);
            cir.setReturnValue(geometry.bake(UtilKt.uncheckedCast(this), modelBaker, spriteSupplier::apply, bakeSettings, overrides, id, hasDepth));
        }
    }

    @Inject(method = "resolveParents", at = @At("HEAD"))
    private void handleCustomResolveParents(Function<Identifier, UnbakedModel> models, CallbackInfo ci) {
        var geometry = getCustomGeometry();
        if (geometry != null) {
            geometry.resolveParents(models::apply, UtilKt.uncheckedCast(this));
        }
    }

    @NotNull
    @Override
    public ModelOverrideList getOverrides(@NotNull ModelBaker modelBaker, @NotNull JsonUnbakedModel model, @NotNull Function1<? super Material, ? extends Sprite> textureSupplier) {
        return overrides.isEmpty() ? ModelOverrideList.EMPTY : new ModelOverrideList(modelBaker, model, overrides);
    }

    @Override
    public void setCustomGeometry(@Nullable UnbakedGeometry<?> geometry) {
        customModel = geometry;
    }

    @Nullable
    @Override
    public UnbakedGeometry<?> getCustomGeometry() {
        return customModel;
    }

    @Override
    public boolean isComponentVisible(@NotNull String name, boolean fallback) {
        var self = UtilKt.<JsonUnbakedModel>uncheckedCast(this);
        return self.parent != null && !visibilityData.hasCustomVisibility(name) ?
                JsonUnbakedModelExt.INSTANCE.isComponentVisible(self.parent, name, fallback) :
                visibilityData.isVisible(name, fallback);
    }

    @NotNull
    @Override
    public VisibilityData getVisibilityData() {
        return visibilityData;
    }

    @NotNull
    @Override
    public AffineTransformation getRootTransform() {
        if (rootTransform != null) return rootTransform;
        return parent != null ? JsonUnbakedModelExt.INSTANCE.getRootTransform(self()) : AffineTransformation.identity();
    }

    private JsonUnbakedModel self() {
        return (JsonUnbakedModel) (Object) this;
    }

    @Override
    public void setRootTransform(@NotNull AffineTransformation transform) {
        rootTransform = transform;
    }
}
