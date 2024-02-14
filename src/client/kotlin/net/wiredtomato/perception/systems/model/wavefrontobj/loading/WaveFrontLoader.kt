package net.wiredtomato.perception.systems.model.wavefrontobj.loading

import com.mojang.blaze3d.vertex.VertexFormat.DrawMode
import net.wiredtomato.perception.systems.math.swizzling.xy
import net.wiredtomato.perception.systems.rendering.shapes.Quad
import net.wiredtomato.perception.systems.rendering.shapes.Triangle
import net.wiredtomato.perception.systems.rendering.shapes.geometry.DrawableGeometricShape
import net.wiredtomato.perception.systems.rendering.shapes.geometry.GeometricTriangle
import net.wiredtomato.perception.systems.model.wavefrontobj.loading.exceptions.ObjParseException
import org.joml.Vector2f
import org.joml.Vector3f
import java.util.*

object WaveFrontLoader {
    fun load(data: String): Map<String, DrawableGeometricShape> {
        val shapes = mutableMapOf<String, DrawableGeometricShape.ComplexMutable>()
        val lines = data.lines().iterator()

        var lineIdx = -1
        var currentShape: DrawableGeometricShape.ComplexMutable? = null
        var faceType: FaceType? = null
        var name: String
        while (lines.hasNext()) {
            var line = lines.next()
            line = line.trim()
            lineIdx++

            //join seperated lines
            var finished = false
            while (line.endsWith("\\")) {
                line = line.substring(0, line.length - 2)
                if (!lines.hasNext()) {
                    finished = true
                    break
                }
                line += " " + lines.next()
                lineIdx++
            }
            if (finished) break

            val tokenizer = StringTokenizer(line)
            if (!tokenizer.hasMoreTokens()) continue

            val identifier = tokenizer.nextToken().lowercase()

            when (identifier) {
                //Vertex coords
                "v" -> {
                    if (currentShape == null) continue
                    currentShape.vertices.add(parseVertex(tokenizer))
                }
                //texture coords
                "vt" -> {
                    if (currentShape == null) continue
                    currentShape.uvs.add(parseUv(tokenizer))
                }
                //face (tri or quad)
                "f" -> {
                    if (currentShape == null) continue
                    val face = parseFace(tokenizer, lineIdx, currentShape.vertices, currentShape.uvs)
                    val cFaceType = if (face is GeometricTriangle) FaceType.TRI else FaceType.QUAD
                    if (faceType == null) {
                        faceType = cFaceType
                        currentShape.drawMode = faceType.toDrawMode()
                    }
                    else if (faceType != cFaceType) throw ObjParseException("Face at line ${lineIdx + 1} had a different number of vertices than other faces")
                    currentShape.faces += face
                }
                //start object
                "o" -> {
                    name = tokenizer.nextToken().lowercase()
                    currentShape = DrawableGeometricShape.mutable()
                    shapes[name] = currentShape
                }
            }
        }

        return shapes.mapValues { it.value.toImmutable() }
    }

    private fun parseFace(tokenizer: StringTokenizer, lineIdx: Int, vertices: List<Vector3f>, uvs: List<Vector2f>): DrawableGeometricShape {
        val verts = mutableListOf<Vector3f>()
        val tUvs = mutableListOf<Vector2f>()
        while (tokenizer.hasMoreTokens()) {
            val rawData = tokenizer.nextToken().lowercase()
            //skip whitespace
            if (rawData.isBlank()) continue

            val data = rawData.split("/")
            val vertRes = runCatching { data[0].toInt() }
            val uvRes = kotlin.runCatching { data[1].toInt() }
            if (vertRes.isFailure) throw ObjParseException("Failed to parse face at line: ${lineIdx + 1} (no vertex index found)")
            val vertIdx = vertRes.getOrThrow()
            val uvIdx = uvRes.getOrNull() ?: -1
            verts += vertices[vertIdx - 1]
            tUvs += if (uvIdx == -1) vertices[vertIdx - 1].xy() else uvs[uvIdx - 1]
        }

        if (verts.size != tUvs.size)
            throw ObjParseException("Face at line: ${lineIdx + 1} doesnt have an equal number uf uvs and vertices")

        return when (verts.size) {
            3 -> Triangle(verts[0], verts[1], verts[2], tUvs[0], tUvs[1], tUvs[2])
            4 -> Quad(verts[0], verts[1], verts[2], verts[3], tUvs[0], tUvs[2], tUvs[2], tUvs[3])
            else -> throw ObjParseException("Face at line ${lineIdx + 1} is not a triangle or quad")
        }
    }

    private fun parseVertex(tokenizer: StringTokenizer): Vector3f {
        val x = tokenizer.nextToken().toFloat()
        val y = tokenizer.nextToken().toFloat()
        val z = tokenizer.nextToken().toFloat()
        //We don't need W-axis
        if (tokenizer.hasMoreTokens()) tokenizer.nextToken()
        return Vector3f(x, y, z)
    }

    private fun parseUv(tokenizer: StringTokenizer): Vector2f {
        val u = tokenizer.nextToken().toFloat()
        val v = tokenizer.nextToken().toFloat()
        //We don't need W-axis
        if (tokenizer.hasMoreTokens()) tokenizer.nextToken()
        return Vector2f(u, v)
    }

    enum class FaceType {
        TRI, QUAD;

        fun toDrawMode(): DrawMode =
            when (this) {
                TRI -> DrawMode.TRIANGLES
                QUAD -> DrawMode.QUADS
            }
    }
}
