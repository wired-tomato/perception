package net.wiredtomato.perception.systems.model

import net.minecraft.client.render.model.BakedQuadFactory
import net.minecraft.client.render.model.json.ItemModelGenerator
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.MissingSprite
import net.minecraft.client.texture.SpriteAtlasTexture
import net.minecraft.util.Identifier
import java.util.regex.Pattern

object UnbakedGeometryHelper {
    private val itemModelGen = ItemModelGenerator()
    private val quadFactory = BakedQuadFactory()

    private val fsPathToId =
        Pattern.compile("(?:.*[\\\\/]assets[\\\\/](?<namespace>[a-z_-]+)[\\\\/]textures[\\\\/])?(?<path>[a-z_\\\\/-]+)\\.png")

    fun resolveDirtyMaterial(tex: String?, owner: JsonUnbakedModel?): Material {
        var tTex = ""
        if (tex == null)
            return Material(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, MissingSprite.getMissingSpriteId())
        if (tex.startsWith("#") && owner != null)
            return owner.resolveSprite(tex)

        val matcher = fsPathToId.matcher(tex)
        if (matcher.matches()) {
            val namespace = matcher.group("namespace")
            val path = matcher.group("path")
            tTex = if (namespace != null) "$namespace:$path" else path
        }

        return Material(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, Identifier(tTex))
    }
}