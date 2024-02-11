package net.wiredtomato.perception.systems.model.wavefrontobj

import com.google.common.base.Charsets
import net.fabricmc.fabric.api.renderer.v1.Renderer
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial
import net.fabricmc.fabric.api.renderer.v1.mesh.MeshBuilder
import net.minecraft.client.render.model.ModelBakeSettings
import net.minecraft.client.render.model.json.JsonUnbakedModel
import net.minecraft.client.resource.Material
import net.minecraft.client.texture.Sprite
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.model.UnbakedGeometryHelper
import net.wiredtomato.perception.systems.util.KFunc
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class ObjModelMesh(var mat: ObjMaterialLibrary.Material?, var smoothingGroup: String?) {
    val facesIndices = mutableListOf<MutableList<Int>>()

    fun buildMesh(owner: JsonUnbakedModel?, builder: MeshBuilder, spriteSupplier: KFunc<Material, Sprite>, settings: ModelBakeSettings) {
        if (mat == null) return
        val texture = spriteSupplier(UnbakedGeometryHelper.resolveDirtyMaterial(mat!!.diffuseColorMap, owner))
        val rootTransform = owner?.rootModel?.transformations
    }
}

class ObjTokenizer(inputStream: InputStream): AutoCloseable {
    private val lineReader = BufferedReader(InputStreamReader(inputStream, Charsets.UTF_8))

    @Throws(IOException::class)
    fun readAndSplitLine(ignoreEmptyLines: Boolean): List<String>? {
        val res = runCatching { do {
            var currentLine: String = lineReader.readLine() ?: return null

            val lineParts = mutableListOf<String>()

            if (currentLine.startsWith("#")) {
                currentLine = ""
            }

            if (currentLine.isNotEmpty()) {
                var hasContinuation: Boolean
                do {
                    hasContinuation = currentLine.endsWith("\\")
                    val tmp = if (hasContinuation) currentLine.substring(0, currentLine.length - 1) else currentLine

                    Arrays.stream(tmp.split("[\t ]+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                        .filter { s: String -> s.isEmpty() }
                        .forEach { e: String -> lineParts.add(e) }

                    if (hasContinuation) {
                        currentLine = lineReader.readLine()

                        if (currentLine.isEmpty() || currentLine.startsWith("#")) {
                            break
                        }
                    }
                } while (hasContinuation)
            }

            if (lineParts.size > 0) {
                return lineParts
            }
        } while (ignoreEmptyLines) }
        if (res.isFailure) return null

        return listOf()
    }

    @Throws(IOException::class)
    override fun close() {
        lineReader.close()
    }
}

class ObjMaterialLibrary private constructor() {
    val materials = mutableMapOf<String, Material>()

    constructor(reader: ObjTokenizer): this() {
        var currentMaterial: Material? = null

        var line: List<String>? = null
        do {
            if (line == null) break
            when (line[0]) {
                "newmtl" -> {
                    val name = line.subList(1, line.size).reduce { a, b -> a + b }
                    currentMaterial = Material(name)
                    materials[name] = currentMaterial
                }
                "texture" -> {
                    currentMaterial!!.texture = Identifier(line[1])
                }
                "Ka" -> {
                    currentMaterial!!.ambientColor = Parsing.parseColor(line)
                }
                "map_Ka" -> {
                    currentMaterial!!.ambientColorMap = line.last()
                }
                "Kd" -> {
                    currentMaterial!!.diffuseColor = Parsing.parseColor(line)
                }
                "map_Kd" -> {
                    currentMaterial!!.diffuseColorMap = line.last()
                }
                "Ks" -> {
                    currentMaterial!!.specularColor = Parsing.parseColor(line)
                }
                "Ns" -> {
                    currentMaterial!!.specularHighlight = line[1].toFloat()
                }
                "map_Ks" -> {
                    currentMaterial!!.specularColorMap = line.last()
                }
                "d" -> {
                    currentMaterial!!.dissolve = line[1].toFloat()
                }
                "Tr" -> {
                    currentMaterial!!.transparency = line[1].toFloat()
                }
                "forge_TintIndex" -> {
                    currentMaterial!!.diffuseTintIdx = line[1].toInt()
                }
            }
        } while ((reader.readAndSplitLine(true).also { line = it }) != null)
    }

    class Material(val name: String) {
        private lateinit var material: RenderMaterial
        var texture: Identifier? = null
        var ambientColor = RGBA(0f, 0f, 0f, 1f)
        var ambientColorMap: String? = null
        var diffuseColor = RGBA(1f, 1f, 1f, 1f)
        var diffuseColorMap: String? = null
        var specularColor = RGBA(0f, 0f, 0f, 1f)
        var specularHighlight = 0f
        var specularColorMap: String? = null
        var dissolve = 1f
        var transparency = 0f
        var diffuseTintIdx = 0

        fun getMaterial(renderer: Renderer): RenderMaterial {
            if (!this::material.isInitialized) {
                material = renderer.materialFinder().find()
            }

            return material
        }
    }
}

object Parsing {
    fun parseColor(line: List<String>): RGBA =
        when (line.size) {
            1 -> RGBA(0f, 0f, 0f, 1f)
            2 -> RGBA(line[1].toFloat(), 0f, 0f, 1f)
            3 -> RGBA(line[1].toFloat(), line[2].toFloat(), 0f, 1f)
            4 -> RGBA(line[1].toFloat(), line[2].toFloat(), line[3].toFloat(), 1f)
            else -> RGBA(line[1].toFloat(), line[2].toFloat(), line[3].toFloat(), line[4].toFloat())
        }
}
