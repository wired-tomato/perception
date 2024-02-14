package net.wiredtomato.perception.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resource.ResourceType
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.ObjLoader
import net.wiredtomato.perception.systems.rendering.handling.RenderHandler
import net.wiredtomato.perception.systems.rendering.item.PerceivedItemRenderer
import net.wiredtomato.perception.systems.rendering.item.TestRenderer
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShapes

object PerceptionClient: ClientModInitializer {
	override fun onInitializeClient() {
		RenderHandler.init()
		PerceivedItemRenderer.init()
		GeometricShapes.init()
		TestRenderer.init()
		ModelLoadingPlugin.register(ObjLoader)
	}
}