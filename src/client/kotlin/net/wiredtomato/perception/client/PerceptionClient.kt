package net.wiredtomato.perception.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceType
import net.wiredtomato.perception.systems.rendering.handling.RenderHandler
import net.wiredtomato.perception.systems.rendering.item.PerceivedItemRenderer
import net.wiredtomato.perception.systems.rendering.item.TestRenderer
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShapes

object PerceptionClient: ClientModInitializer {
	override fun onInitializeClient() {
		RenderHandler.init()
		PerceivedItemRenderer.init()
		TestRenderer.init()
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(GeometricShapes)
	}
}