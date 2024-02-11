package net.wiredtomato.perception.systems.rendering

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.*
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.GameRenderer
import net.minecraft.client.render.ShaderProgram
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper.lerp
import net.wiredtomato.perception.systems.color.RGBA
import net.wiredtomato.perception.systems.math.*
import net.wiredtomato.perception.systems.rendering.VFX.ScreenVertexPlacementSupplier
import net.wiredtomato.perception.systems.rendering.shapes.Drawable
import net.wiredtomato.perception.systems.util.KConsumer
import net.wiredtomato.perception.systems.util.KFunc
import net.wiredtomato.perception.systems.util.KSupplier
import net.wiredtomato.perception.systems.util.accept
import org.joml.Matrix4f
import org.joml.Vector3f
import org.joml.Vector4f
import java.awt.Color
import java.util.stream.Collectors
import kotlin.properties.Delegates

@Suppress("unused", "duplicates", "MemberVisibilityCanBePrivate")
object VFX {
    class ScreenVFXBuilder {
        var color = RGBA(1f, 1f, 1f, 1f)
        var light = -1
        var textureSpace = TextureSpaceBorder(0f, 0f, 1f, 1f)
        var screenSpace = ScreenSpaceBorder(0f, 0f, 1f, 1f)
        var z by Delegates.notNull<Int>()

        lateinit var format: VertexFormat
        var shader: KSupplier<ShaderProgram> = { GameRenderer.getPositionTexShader()!! }
        var texture: Identifier? = null
        lateinit var placementSupplier: ScreenVertexPlacementSupplier
        var bufferBuilder: BufferBuilder = Tessellator.getInstance().bufferBuilder

        fun posTexDefaultFormat() = apply {
            placementSupplier = ScreenVertexPlacementSupplier { builder, last, x, y, u, v ->
                builder.vertex(last, x, y, z.toFloat()).uv(u, v).next()
            }
            format = VertexFormats.POSITION_TEXTURE
        }

        fun posColorDefaultFormat() = apply {
            placementSupplier = ScreenVertexPlacementSupplier { builder, last, x, y, _, _ ->
                builder.vertex(last, x, y, z.toFloat()).color(color.r, color.g, color.b, color.a).next()
            }
            format = VertexFormats.POSITION_COLOR
        }

        fun posColorTexDefaultFormat() = apply {
            placementSupplier = ScreenVertexPlacementSupplier { builder, last, x, y, u, v ->
                builder.vertex(last, x, y, z.toFloat())
                    .color(color.r, color.g, color.b, color.a)
                    .uv(u, v).next()
            }
            format = VertexFormats.POSITION_COLOR_TEXTURE
        }

        fun posColorTexLightmapDefaultFormat() = apply {
            placementSupplier = ScreenVertexPlacementSupplier { builder, last, x, y, u, v ->
                builder.vertex(last, x, y, z.toFloat())
                    .color(color.r, color.g, color.b, color.a)
                    .uv(u, v).next()
            }
            format = VertexFormats.POSITION_COLOR_TEXTURE_LIGHT
        }

        fun format(format: VertexFormat) = apply { this.format = format }
        fun shaderTexture(texture: Identifier) = apply { this.texture = texture }
        fun shader(shader: KSupplier<ShaderProgram>) = apply { this.shader = shader }
        fun shader(shader: ShaderProgram) = apply { this.shader = { shader } }
        fun vertexSupplier(supplier: ScreenVertexPlacementSupplier) = apply { this.placementSupplier = supplier }
        fun overrideBufferBuilder(builder: BufferBuilder) = apply { this.bufferBuilder = builder }
        fun light(light: Int) = apply { this.light = light }
        fun color(color: Color) = apply { this.color(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat()) }
        fun color(color: Color, a: Float) = apply { this.color(color).alpha(a) }
        fun color(r: Float, g: Float, b: Float, a: Float) = apply { color(r, g, b).alpha(a) }
        fun color(r: Float, g: Float, b: Float) = apply {
            this.color.r = r / 255
            this.color.g = g / 255
            this.color.b = b / 255
        }
        fun alpha(a: Float) = apply { this.color.a = a }
        fun posSize(x: Float, y: Float, width: Float, height: Float) = apply { pos(x, y, x + width, y + height) }
        fun pos(x0: Float, y0: Float, x1: Float, y1: Float) = apply {
            this.screenSpace.x0 = x0
            this.screenSpace.y0 = y0
            this.screenSpace.x1 = x1
            this.screenSpace.y1 = y1
        }
        fun screenSpace(screenSpace: ScreenSpaceBorder) = apply { this.screenSpace = screenSpace }
        fun z(z: Int) = apply { this.z = z }
        fun uvSize(u: Float, v: Float, width: Float, height: Float, canvasSize: Float) = apply {
            uvSize(u, v, width, height, canvasSize, canvasSize)
        }
        fun uvSize(u: Float, v: Float, width: Float, height: Float, canvasSizeX: Float, canvasSizeY: Float) = apply {
            uvSize(u / canvasSizeX, v / canvasSizeY, width / canvasSizeX, height / canvasSizeY)
        }
        fun uvSize(u: Float, v: Float, width: Float, height: Float) = apply { uv(u, v, u + width, u + height) }
        fun uv(u0: Float, v0: Float, u1: Float, v1: Float, canvasSize: Float) = apply {
            uv(u0, v0, u1, v1, canvasSize, canvasSize)
        }
        fun uv(u0: Float, v0: Float, u1: Float, v1: Float, canvasSizeX: Float, canvasSizeY: Float) = apply {
            uv(u0 / canvasSizeX, v0 / canvasSizeY, u1 / canvasSizeX, v1 / canvasSizeY)
        }
        fun uv(u0: Float, v0: Float, u1: Float, v1: Float) = apply {
            this.textureSpace.u0 = u0
            this.textureSpace.v0 = v0
            this.textureSpace.u1 = u1
            this.textureSpace.v1 = v1
        }
        fun textureSpace(textureSpace: TextureSpaceBorder) = apply { this.textureSpace = textureSpace }
        fun begin() = apply { bufferBuilder.begin(VertexFormat.DrawMode.QUADS, format) }

        fun blit(stack: MatrixStack) = apply {
            val last = stack.peek().model
            RenderSystem.setShader(shader)
            if (texture != null) {
                RenderSystem.setShaderTexture(0, texture)
            }
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x0, screenSpace.y1, textureSpace.u0, textureSpace.v1)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x1, screenSpace.y1, textureSpace.u1, textureSpace.v1)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x1, screenSpace.y0, textureSpace.u1, textureSpace.v0)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x0, screenSpace.y0, textureSpace.u0, textureSpace.v0)
        }

        fun blit(stack: MatrixStack, gradiantConsumer: KConsumer<ScreenVFXBuilder>) = apply {
            val last = stack.peek().model
            RenderSystem.setShader(shader)
            if (texture != null) {
                RenderSystem.setShaderTexture(0, texture)
            }
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x0, screenSpace.y1, textureSpace.u0, textureSpace.v1)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x1, screenSpace.y1, textureSpace.u1, textureSpace.v1)
            gradiantConsumer(this)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x1, screenSpace.y0, textureSpace.u1, textureSpace.v0)
            placementSupplier.placeVertex(bufferBuilder, last, screenSpace.x0, screenSpace.y0, textureSpace.u0, textureSpace.v0)
        }

        fun run(consumer: KConsumer<ScreenVFXBuilder>) = apply { consumer(this) }

        fun end() = apply { BufferRenderer.drawWithShader(bufferBuilder.end()) }

        fun draw(stack: MatrixStack) = apply {
            if (bufferBuilder.isBuilding) bufferBuilder.end()
            begin()
            blit(stack)
            end()
        }
    }

    fun interface ScreenVertexPlacementSupplier {
        fun placeVertex(builder: BufferBuilder, last: Matrix4f, x: Float, y: Float, u: Float, v: Float)
    }

    class WorldVFXBuilder {
        private var color = RGBA(1f, 1f, 1f, 1f)
        private var offset = Vector3f(0f, 0f, 0f)
        private var light = FULL_BRIGHT
        private var textureSpace = TextureSpaceBorder(0f, 0f, 1f, 1f)
        private lateinit var format: VertexFormat
        private lateinit var placementSupplier: WorldVertexPlacementSupplier

        fun posColorDefaultFormat() = apply {
            vertexSupplier { consumer, last, x, y, z, _, _ ->
                if (last == null) consumer.vertex(x.toDouble(), y.toDouble(), z.toDouble()).color(color.r, color.g, color.b, color.a).next()
                else consumer.vertex(last, x, y, z).color(color.r, color.g, color.b, color.a).next()
            }.format(VertexFormats.POSITION_COLOR)
        }

        fun posColorLightmapDefaultFormat() = apply {
            vertexSupplier { consumer, last, x, y, z, _, _ ->
                if (last == null) consumer.vertex(x.toDouble(), y.toDouble(), z.toDouble()).color(color.r, color.g, color.b, color.a).light(light).next()
                else consumer.vertex(last, x, y, z).color(color.r, color.g, color.b, color.a).light(light).next()
            }.format(VertexFormats.POSITION_COLOR_LIGHT)
        }

        fun posTexDefaultFormat() = apply {
            vertexSupplier { consumer, last, x, y, z, u, v ->
                if (last == null) consumer.vertex(x.toDouble(), y.toDouble(), z.toDouble()).uv(u ,v).next()
                else consumer.vertex(last, x, y, z).uv(u ,v).next()
            }.format(VertexFormats.POSITION_TEXTURE)
        }

        fun posColorTexDefaultFormat() = apply {
            vertexSupplier { consumer, last, x, y, z, u, v ->
                if (last == null) consumer.vertex(x.toDouble(), y.toDouble(), z.toDouble()).color(color.r, color.g, color.b, color.a).uv(u, v).next()
                else consumer.vertex(last, x, y, z).color(color.r, color.g, color.b, color.a).uv(u, v).next()
            }.format(VertexFormats.POSITION_COLOR_TEXTURE)
        }

        fun posColorTexLightmapDefaultFormat() = apply {
            vertexSupplier { consumer, last, x, y, z, u, v ->
                if (last == null) consumer.vertex(x.toDouble(), y.toDouble(), z.toDouble()).color(color.r, color.g, color.b, color.a).uv(u, v).light(light).next()
                else consumer.vertex(last, x, y, z).color(color.r, color.g, color.b, color.a).uv(u, v).light(light).next()
            }.format(VertexFormats.POSITION_COLOR_TEXTURE_LIGHT)
        }

        fun format(format: VertexFormat) = apply { this.format = format }
        fun vertexSupplier(supplier: WorldVertexPlacementSupplier) = apply { this.placementSupplier = supplier }
        fun color(color: Color) = apply { this.color(color.red.toFloat(), color.green.toFloat(), color.blue.toFloat()) }
        fun color(color: Color, a: Float) = apply { this.color(color).alpha(a) }
        fun color(r: Float, g: Float, b: Float, a: Float) = apply { color(r, g, b).alpha(a) }
        fun color(r: Float, g: Float, b: Float) = apply {
            this.color.r = r / 255
            this.color.g = g / 255
            this.color.b = b / 255
        }
        fun alpha(a: Float) = apply { this.color.a = a }
        fun offset(xOffset: Float, yOffset: Float, zOffset: Float) = apply {
            this.offset.x = xOffset
            this.offset.y = yOffset
            this.offset.z = zOffset
        }
        fun offset(offset: Vector3f) = apply { this.offset = offset }
        fun light(light: Int) = apply { this.light = light }
        fun uv(u0: Float, v0: Float, u1: Float, v1: Float) = apply {
            this.textureSpace.u0 = u0
            this.textureSpace.v0 = v0
            this.textureSpace.u1 = u1
            this.textureSpace.v1 = v1
        }
        fun textureSpace(textureSpace: TextureSpaceBorder) = apply { this.textureSpace = textureSpace }

        fun renderTrail(consumer: VertexConsumer, stack: MatrixStack, trailSegments: List<Vector4f>, widthFunc: KFunc<Float, Float>) = apply {
            renderTrail(consumer, stack, trailSegments, widthFunc) {}
        }

        fun renderTrail(consumer: VertexConsumer, stack: MatrixStack, trailSegments: List<Vector4f>, widthFunc: KFunc<Float, Float>, vfxOperator: KConsumer<Float>) =
            apply { renderTrail(consumer, stack.peek().model, trailSegments, widthFunc, vfxOperator) }

        fun renderTrail(consumer: VertexConsumer, pose: Matrix4f, trailSegments: List<Vector4f>, widthFunc: KFunc<Float, Float>, vfxOperator: KConsumer<Float>) = apply {
            if (trailSegments.size < 3) return this
            val segments = trailSegments.stream().map { v -> Vector4f(v.x, v.y, v.z, v.w) }.collect(Collectors.toList())
            segments.forEach {
                it.add(offset.x, offset.y, offset.z, 0f)
                it.mulTranspose(pose)
            }

            val lastIdx = segments.lastIndex
            val increment = 1.0f / (lastIdx - 1)
            val points = mutableListOf<Branch>()
            for (i in 0..<lastIdx) {
                val width = widthFunc(increment * i)
                val start = segments[i]
                val end = trailSegments[i + 1]
                points += Branch(midpoint(start, end), screenSpaceQuadOffsets(start, end, width))
            }
            renderPoints(consumer, points, textureSpace.u0, textureSpace.v0, textureSpace.u1, textureSpace.v1, vfxOperator)
        }

        fun renderPoints(consumer: VertexConsumer, points: List<Branch>, u0: Float, v0: Float, u1: Float, v1: Float, vfxOperator: KConsumer<Float>) = apply {
            val lastIdx = points.lastIndex
            val increment = 1.0f / lastIdx
            vfxOperator(0f)
            points[0].renderStart(consumer, placementSupplier, u0, v0, u1, lerp(increment, v0, v1))
            for (i in 1..<lastIdx) {
                val current = lerp(i * increment, v0, v1)
                vfxOperator(current)
                points[i].renderMid(consumer, placementSupplier, u0, current, u1, current)
            }
            vfxOperator.accept(1f)
            points[lastIdx].renderEnd(consumer, placementSupplier, u0, lerp(lastIdx * increment,v0, v1), u1, v1)
        }

        fun renderBeam(consumer: VertexConsumer, stack: MatrixStack, start: Vector3f, end: Vector3f, width: Float) = apply {
            val client = MinecraftClient.getInstance()
            start.add(offset)
            end.add(offset)
            stack.translate(-start.x, -start.y, -start.z)
            val camPos = client.blockEntityRenderDispatcher.camera.pos.toVector3f()
            val delta = end.sub(start, Vector3f())
            val normal = start.sub(camPos, Vector3f()).cross(delta).normalize().mul(width / 2)
            val last = stack.peek().model
            val positions = listOf(start.sub(normal, Vector3f()), start.add(normal, Vector3f()), end.add(normal, Vector3f()), end.sub(normal, Vector3f()))

            placementSupplier.placeVertex(consumer, last, positions[0].x, positions[0].y, positions[0].z, textureSpace.u0, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[1].x, positions[1].y, positions[1].z, textureSpace.u1, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[2].x, positions[2].y, positions[2].z, textureSpace.u1, textureSpace.v0)
            placementSupplier.placeVertex(consumer, last, positions[3].x, positions[3].y, positions[3].z, textureSpace.u0, textureSpace.v0)
            stack.translate(start.x, start.y, start.z)
        }

        fun renderQuad(consumer: VertexConsumer, stack: MatrixStack, size: Float) = apply {
            renderQuad(consumer, stack, size, size)
        }

        fun renderQuad(consumer: VertexConsumer, stack: MatrixStack, width: Float, height: Float) = apply {
            val positions = listOf(Vector3f(-1f, -1f, 0f), Vector3f(1f, -1f, 0f), Vector3f(1f, 1f, 0f), Vector3f(-1f, 1f, 0f))
            renderQuad(consumer, stack, positions, width, height)
        }

        fun renderQuad(consumer: VertexConsumer, stack: MatrixStack, positions: List<Vector3f>, size: Float) = apply {
            renderQuad(consumer, stack, positions, size, size)
        }

        fun renderQuad(consumer: VertexConsumer, stack: MatrixStack, positions: List<Vector3f>, width: Float, height: Float) = apply {
            val last = stack.peek().model
            stack.translate(offset.x, offset.y, offset.z)
            for (position in positions) {
                position.mul(width, height, width)
            }
            placementSupplier.placeVertex(consumer, last, positions[0].x, positions[0].y, positions[0].z, textureSpace.u0, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[1].x, positions[1].y, positions[1].z, textureSpace.u1, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[2].x, positions[2].y, positions[2].z, textureSpace.u1, textureSpace.v0)
            placementSupplier.placeVertex(consumer, last, positions[3].x, positions[3].y, positions[3].z, textureSpace.u0, textureSpace.v0)
            stack.translate(-offset.x, -offset.y, -offset.z)
        }

        fun renderScreenSpaceQuad(consumer: VertexConsumer, stack: MatrixStack, size: Float) = apply {
            renderScreenSpaceQuad(consumer, stack, size, size)
        }

        fun renderScreenSpaceQuad(consumer: VertexConsumer, stack: MatrixStack, width: Float, height: Float) = apply {
            val positions = listOf(Vector3f(-1f, -1f, 0f), Vector3f(1f, -1f, 0f), Vector3f(1f, 1f, 0f), Vector3f(-1f, 1f, 0f))
            renderScreenSpaceQuad(consumer, stack, positions, width, height)
        }

        fun renderScreenSpaceQuad(consumer: VertexConsumer, stack: MatrixStack, positions: List<Vector3f>, size: Float) = apply {
            renderScreenSpaceQuad(consumer, stack, positions, size, size)
        }

        fun renderScreenSpaceQuad(consumer: VertexConsumer, stack: MatrixStack, positions: List<Vector3f>, width: Float, height: Float) = apply {
            val last = stack.peek().model
            for (position in positions) {
                position.mul(width, height, width)
                position.mulTranspose(stack.peek().normal)
            }
            placementSupplier.placeVertex(consumer, last, positions[0].x, positions[0].y, positions[0].z, textureSpace.u0, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[1].x, positions[1].y, positions[1].z, textureSpace.u1, textureSpace.v1)
            placementSupplier.placeVertex(consumer, last, positions[2].x, positions[2].y, positions[2].z, textureSpace.u1, textureSpace.v0)
            placementSupplier.placeVertex(consumer, last, positions[3].x, positions[3].y, positions[3].z, textureSpace.u0, textureSpace.v0)
            stack.translate(-offset.x, -offset.y, -offset.z)
        }

        fun renderShape(consumer: VertexConsumer, stack: MatrixStack, shape: Drawable) {
            shape.draw(consumer, { vConsumer, last, x, y, z, _, _, _, _, u, v, _ -> placementSupplier.placeVertex(vConsumer, last, x, y, z, u, v) }, stack, color, light)
        }
    }

    fun interface WorldVertexPlacementSupplier {
        fun placeVertex(consumer: VertexConsumer, last: Matrix4f?, x: Float, y: Float, z: Float, u: Float, v: Float)
    }
}
