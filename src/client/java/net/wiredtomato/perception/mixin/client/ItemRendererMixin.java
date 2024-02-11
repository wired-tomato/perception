package net.wiredtomato.perception.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.wiredtomato.perception.Perception;
import net.wiredtomato.perception.systems.item.PerceivedItem;
import net.wiredtomato.perception.systems.rendering.VFX;
import net.wiredtomato.perception.systems.rendering.item.PerceivedItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Unique
    VFX.WorldVFXBuilder builder = new VFX.WorldVFXBuilder().posColorTexLightmapDefaultFormat();

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At("HEAD"))
    public void renderPerceivedItemBefore(ItemStack stack, ModelTransformationMode modelTransformationMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo ci) {
        if (stack.getItem() instanceof PerceivedItem pItem) {
            matrices.push();
            var renderer = PerceivedItemRenderer.REGISTRY.get(pItem.getRendererId());
            if (renderer == null) {
                matrices.pop();
                return;
            }
            float time = ((float) (MinecraftClient.getInstance().world.getTime() % 2400000L) + MinecraftClient.getInstance().getTickDelta());
            renderer.renderItem(matrices, modelTransformationMode, vertexConsumers, builder, stack, time, false);
            matrices.pop();
        }
    }
}
