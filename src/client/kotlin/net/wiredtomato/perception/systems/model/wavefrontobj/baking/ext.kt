package net.wiredtomato.perception.systems.model.wavefrontobj.baking

import net.minecraft.client.render.model.ModelBaker
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.render.model.json.ModelOverrideList
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.Sprite
import net.minecraft.util.math.AffineTransformation
import net.minecraft.util.math.Direction
import net.wiredtomato.perception.systems.util.KFunc
import net.wiredtomato.perception.systems.util.inlineCast
import org.joml.Matrix3f
import org.joml.Vector3f
import org.joml.Vector4f

interface JsonUnbakedModelExtMedium {
    fun getOverrides(modelBaker: ModelBaker, model: JsonUnbakedModel, textureSupplier: KFunc<Material, Sprite>): ModelOverrideList
    fun setCustomGeometry(geometry: UnbakedGeometry<*>?)
    fun getCustomGeometry(): UnbakedGeometry<*>?
    fun isComponentVisible(name: String, fallback: Boolean): Boolean
    fun getVisibilityData(): VisibilityData
    fun getRootTransform(): AffineTransformation
    fun setRootTransform(transform: AffineTransformation)
}

object JsonUnbakedModelExt {
    val JsonUnbakedModel.visibilityData
        get() = inlineCast<JsonUnbakedModelExtMedium>(this).getVisibilityData()
    var JsonUnbakedModel.customGeometry
        get() = inlineCast<JsonUnbakedModelExtMedium>(this).getCustomGeometry()
        set(value) = inlineCast<JsonUnbakedModelExtMedium>(this).setCustomGeometry(value)
    var JsonUnbakedModel.rootTransform
        get() = inlineCast<JsonUnbakedModelExtMedium>(this).getRootTransform()
        set(value) = inlineCast<JsonUnbakedModelExtMedium>(this).setRootTransform(value)

    fun JsonUnbakedModel.getOverrides(modelBaker: ModelBaker, model: JsonUnbakedModel, textureSupplier: KFunc<Material, Sprite>) =
        inlineCast<JsonUnbakedModelExtMedium>(this).getOverrides(modelBaker, model, textureSupplier)
    fun JsonUnbakedModel.isComponentVisible(name: String, fallback: Boolean) =
        inlineCast<JsonUnbakedModelExtMedium>(this).isComponentVisible(name, fallback)
}

data class VisibilityData(
    private val data: MutableMap<String, Boolean> = mutableMapOf()
) {
    fun hasCustomVisibility(part: String): Boolean {
        return data.containsKey(part)
    }

    fun isVisible(part: String, fallback: Boolean): Boolean {
        return data.getOrDefault(part, fallback)
    }

    fun setVisibilityState(partName: String, type: Boolean) {
        data[partName] = type
    }

    fun copyFrom(visibilityData: VisibilityData) {
        data.clear()
        data.putAll(visibilityData.data)
    }
}
