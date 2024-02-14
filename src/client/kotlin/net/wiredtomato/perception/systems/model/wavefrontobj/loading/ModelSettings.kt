package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import net.minecraft.util.Identifier

class ModelSettings(
    val id: Identifier,
    val automaticCulling: Boolean,
    val shadeQuads: Boolean,
    val flipV: Boolean,
    val emissiveAmbient: Boolean,
    val mtlOverride: String?,
)
