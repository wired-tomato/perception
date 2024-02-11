package net.wiredtomato.perception.systems.item

import net.minecraft.item.Item
import net.minecraft.util.Identifier

abstract class PerceivedItem(settings: Settings): Item(settings) {
    open fun getRendererId(): Identifier? = null
}