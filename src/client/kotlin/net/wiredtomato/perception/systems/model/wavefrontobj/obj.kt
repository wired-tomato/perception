package net.wiredtomato.perception.systems.model.wavefrontobj

import com.google.common.base.Charsets
import net.fabricmc.fabric.api.renderer.v1.Renderer
import net.fabricmc.fabric.api.renderer.v1.material.RenderMaterial
import net.minecraft.util.Identifier
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricShape
import net.wiredtomato.perception.systems.rendering.shapes.geometry.NormalGeometricShape
import org.joml.Vector2f
import org.joml.Vector3f
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

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

    constructor(tokenizer: ObjTokenizer): this() {
        var currentMaterial: Material? = null

        var line: List<String>? = tokenizer.readAndSplitLine(true)
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
                "forge_TintIndex", "perception_TintIndex" -> {
                    currentMaterial!!.diffuseTintIndex = line[1].toInt()
                }
            }
        } while ((tokenizer.readAndSplitLine(true).also { line = it }) != null)
    }

    class Material(val name: String) {
        private lateinit var material: RenderMaterial
        var texture: Identifier? = null
        var ambientColor = RGBA(0f, 0f, 0f, 1f)
        var ambientColorMap: String? = null
        var diffuseColor = RGBA(1f, 1f, 1f, 1f)
        var diffuseColorMap: String? = null
        var diffuseTintIndex = 0
        var specularColor = RGBA(0f, 0f, 0f, 1f)
        var specularHighlight = 0f
        var specularColorMap: String? = null
        var dissolve = 1f
        var transparency = 0f

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

class ObjTri(
    val v0: Vector3f, val v1: Vector3f, val v2: Vector3f,
    val uv0: Vector2f, val uv1: Vector2f, val uv2: Vector2f,
    val n0: Vector3f, val n1: Vector3f, val n2: Vector3f
): NormalGeometricShape {
    override fun vertices(): List<Vector3f> = listOf(v0, v1, v2)
    override fun uvs(): List<Vector2f> = listOf(uv0, uv1, uv2)
    override fun normals(): List<Vector3f> = listOf(n0, n1, n2)
    override fun faces(): List<NormalGeometricShape> = listOf(this)
    override fun isQuads(): Boolean = false
}

class ObjQuad(
    val v0: Vector3f, val v1: Vector3f, val v2: Vector3f, val v3: Vector3f,
    val uv0: Vector2f, val uv1: Vector2f, val uv2: Vector2f, val uv3: Vector2f,
    val n0: Vector3f, val n1: Vector3f, val n2: Vector3f, val n3: Vector3f
): NormalGeometricShape {
    override fun vertices(): List<Vector3f> = listOf(v0, v1, v2, v3)
    override fun uvs(): List<Vector2f> = listOf(uv0, uv1, uv2, uv3)
    override fun normals(): List<Vector3f> = listOf(n0, n1, n2, n3)
    override fun faces(): List<NormalGeometricShape> = listOf(this)
    override fun isQuads() = true
}
