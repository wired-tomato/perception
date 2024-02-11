package net.wiredtomato.perception

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.item.SimplePerceivedItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object Perception: ModInitializer {
	val MOD_ID = "perception"
    val logger: Logger = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger.info("Hello Fabric world!")
		Registry.register(Registries.ITEM, id("test"), SimplePerceivedItem(FabricItemSettings(), id("test_renderer")))
	}

	fun id(path: String) = Identifier(MOD_ID, path)
}