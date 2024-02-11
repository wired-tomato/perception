package net.wiredtomato.perception.systems.item

import net.minecraft.util.Identifier

class SimplePerceivedItem(settings: Settings, private val renderer: Identifier?): PerceivedItem(settings) {
    override fun getRendererId(): Identifier? = renderer
}