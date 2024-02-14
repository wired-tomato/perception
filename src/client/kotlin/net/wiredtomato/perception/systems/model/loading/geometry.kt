package net.wiredtomato.perception.systems.model.loading

import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape
import net.wiredtomato.perception.systems.util.KFunc
import java.util.Optional

interface GeometryLoader {
    fun loadGeometry(resourceManager: ResourceManager, id: Identifier)
    fun getLoaded(): List<MappedGeometry>
    fun fileExtensions(): List<String>
}

data class MappedGeometry(val id: Identifier, val geometry: GeometricShape)

infix fun <T: GeometricShape> T.mappedTo(id: Identifier) = MappedGeometry(id, this)

infix fun <T, V> Optional<T>.ifPresentRun(f: KFunc<T, V>): V? {
    return if (this.isPresent) f(this.get())
    else null
}
