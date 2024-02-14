@file:Suppress("unused")
package net.wiredtomato.perception.systems.math.swizzling

import org.joml.Vector2f
import org.joml.Vector2i
import org.joml.Vector3d
import org.joml.Vector3f
import org.joml.Vector3i
import org.joml.Vector4d
import org.joml.Vector4f
import org.joml.Vector4i

fun Vector2i.xx(): Vector2i = Vector2i(x, x)

fun Vector2i.xy(): Vector2i = Vector2i(x, y)

fun Vector2i.yx(): Vector2i = Vector2i(y, x)

fun Vector2i.yy(): Vector2i = Vector2i(y, y)

fun Vector2f.xx(): Vector2f = Vector2f(x, x)

fun Vector2f.xy(): Vector2f = Vector2f(x, y)

fun Vector2f.yx(): Vector2f = Vector2f(y, x)

fun Vector2f.yy(): Vector2f = Vector2f(y, y)

fun Vector3i.xx(): Vector2i = Vector2i(x, x)

fun Vector3i.xy(): Vector2i = Vector2i(x, y)

fun Vector3i.xz(): Vector2i = Vector2i(x, z)

fun Vector3i.yx(): Vector2i = Vector2i(y, x)

fun Vector3i.yy(): Vector2i = Vector2i(y, y)

fun Vector3i.yz(): Vector2i = Vector2i(y, z)

fun Vector3i.zx(): Vector2i = Vector2i(z, x)

fun Vector3i.zy(): Vector2i = Vector2i(z, y)

fun Vector3i.zz(): Vector2i = Vector2i(z, z)

fun Vector3i.xxx(): Vector3i = Vector3i(x, x, x)

fun Vector3i.xxy(): Vector3i = Vector3i(x, x, y)

fun Vector3i.xxz(): Vector3i = Vector3i(x, x, z)

fun Vector3i.xyx(): Vector3i = Vector3i(x, y, x)

fun Vector3i.xyy(): Vector3i = Vector3i(x, y, y)

fun Vector3i.xyz(): Vector3i = Vector3i(x, y, z)

fun Vector3i.xzx(): Vector3i = Vector3i(x, z, x)

fun Vector3i.xzy(): Vector3i = Vector3i(x, z, y)

fun Vector3i.xzz(): Vector3i = Vector3i(x, z, z)

fun Vector3i.yxx(): Vector3i = Vector3i(y, x, x)

fun Vector3i.yxy(): Vector3i = Vector3i(y, x, y)

fun Vector3i.yxz(): Vector3i = Vector3i(y, x, z)

fun Vector3i.yyx(): Vector3i = Vector3i(y, y, x)

fun Vector3i.yyy(): Vector3i = Vector3i(y, y, y)

fun Vector3i.yyz(): Vector3i = Vector3i(y, y, z)

fun Vector3i.yzx(): Vector3i = Vector3i(y, z, x)

fun Vector3i.yzy(): Vector3i = Vector3i(y, z, y)

fun Vector3i.yzz(): Vector3i = Vector3i(y, z, z)

fun Vector3i.zxx(): Vector3i = Vector3i(z, x, x)

fun Vector3i.zxy(): Vector3i = Vector3i(z, x, y)

fun Vector3i.zxz(): Vector3i = Vector3i(z, x, z)

fun Vector3i.zyx(): Vector3i = Vector3i(z, y, x)

fun Vector3i.zyy(): Vector3i = Vector3i(z, y, y)

fun Vector3i.zyz(): Vector3i = Vector3i(z, y, z)

fun Vector3i.zzx(): Vector3i = Vector3i(z, z, x)

fun Vector3i.zzy(): Vector3i = Vector3i(z, z, y)

fun Vector3i.zzz(): Vector3i = Vector3i(z, z, z)

fun Vector3f.xx(): Vector2f = Vector2f(x, x)

fun Vector3f.xy(): Vector2f = Vector2f(x, y)

fun Vector3f.xz(): Vector2f = Vector2f(x, z)

fun Vector3f.yx(): Vector2f = Vector2f(y, x)

fun Vector3f.yy(): Vector2f = Vector2f(y, y)

fun Vector3f.yz(): Vector2f = Vector2f(y, z)

fun Vector3f.zx(): Vector2f = Vector2f(z, x)

fun Vector3f.zy(): Vector2f = Vector2f(z, y)

fun Vector3f.zz(): Vector2f = Vector2f(z, z)

fun Vector3f.xxx(): Vector3f = Vector3f(x, x, x)

fun Vector3f.xxy(): Vector3f = Vector3f(x, x, y)

fun Vector3f.xxz(): Vector3f = Vector3f(x, x, z)

fun Vector3f.xyx(): Vector3f = Vector3f(x, y, x)

fun Vector3f.xyy(): Vector3f = Vector3f(x, y, y)

fun Vector3f.xyz(): Vector3f = Vector3f(x, y, z)

fun Vector3f.xzx(): Vector3f = Vector3f(x, z, x)

fun Vector3f.xzy(): Vector3f = Vector3f(x, z, y)

fun Vector3f.xzz(): Vector3f = Vector3f(x, z, z)

fun Vector3f.yxx(): Vector3f = Vector3f(y, x, x)

fun Vector3f.yxy(): Vector3f = Vector3f(y, x, y)

fun Vector3f.yxz(): Vector3f = Vector3f(y, x, z)

fun Vector3f.yyx(): Vector3f = Vector3f(y, y, x)

fun Vector3f.yyy(): Vector3f = Vector3f(y, y, y)

fun Vector3f.yyz(): Vector3f = Vector3f(y, y, z)

fun Vector3f.yzx(): Vector3f = Vector3f(y, z, x)

fun Vector3f.yzy(): Vector3f = Vector3f(y, z, y)

fun Vector3f.yzz(): Vector3f = Vector3f(y, z, z)

fun Vector3f.zxx(): Vector3f = Vector3f(z, x, x)

fun Vector3f.zxy(): Vector3f = Vector3f(z, x, y)

fun Vector3f.zxz(): Vector3f = Vector3f(z, x, z)

fun Vector3f.zyx(): Vector3f = Vector3f(z, y, x)

fun Vector3f.zyy(): Vector3f = Vector3f(z, y, y)

fun Vector3f.zyz(): Vector3f = Vector3f(z, y, z)

fun Vector3f.zzx(): Vector3f = Vector3f(z, z, x)

fun Vector3f.zzy(): Vector3f = Vector3f(z, z, y)

fun Vector3f.zzz(): Vector3f = Vector3f(z, z, z)

fun Vector3d.xx(): Vector2f = Vector2f(x.toFloat(), x.toFloat())

fun Vector3d.xy(): Vector2f = Vector2f(x.toFloat(), y.toFloat())

fun Vector3d.xz(): Vector2f = Vector2f(x.toFloat(), z.toFloat())

fun Vector3d.yx(): Vector2f = Vector2f(y.toFloat(), x.toFloat())

fun Vector3d.yy(): Vector2f = Vector2f(y.toFloat(), y.toFloat())

fun Vector3d.yz(): Vector2f = Vector2f(y.toFloat(), z.toFloat())

fun Vector3d.zx(): Vector2f = Vector2f(z.toFloat(), x.toFloat())

fun Vector3d.zy(): Vector2f = Vector2f(z.toFloat(), y.toFloat())

fun Vector3d.zz(): Vector2f = Vector2f(z.toFloat(), z.toFloat())

fun Vector3d.xxx(): Vector3d = Vector3d(x, x, x)

fun Vector3d.xxy(): Vector3d = Vector3d(x, x, y)

fun Vector3d.xxz(): Vector3d = Vector3d(x, x, z)

fun Vector3d.xyx(): Vector3d = Vector3d(x, y, x)

fun Vector3d.xyy(): Vector3d = Vector3d(x, y, y)

fun Vector3d.xyz(): Vector3d = Vector3d(x, y, z)

fun Vector3d.xzx(): Vector3d = Vector3d(x, z, x)

fun Vector3d.xzy(): Vector3d = Vector3d(x, z, y)

fun Vector3d.xzz(): Vector3d = Vector3d(x, z, z)

fun Vector3d.yxx(): Vector3d = Vector3d(y, x, x)

fun Vector3d.yxy(): Vector3d = Vector3d(y, x, y)

fun Vector3d.yxz(): Vector3d = Vector3d(y, x, z)

fun Vector3d.yyx(): Vector3d = Vector3d(y, y, x)

fun Vector3d.yyy(): Vector3d = Vector3d(y, y, y)

fun Vector3d.yyz(): Vector3d = Vector3d(y, y, z)

fun Vector3d.yzx(): Vector3d = Vector3d(y, z, x)

fun Vector3d.yzy(): Vector3d = Vector3d(y, z, y)

fun Vector3d.yzz(): Vector3d = Vector3d(y, z, z)

fun Vector3d.zxx(): Vector3d = Vector3d(z, x, x)

fun Vector3d.zxy(): Vector3d = Vector3d(z, x, y)

fun Vector3d.zxz(): Vector3d = Vector3d(z, x, z)

fun Vector3d.zyx(): Vector3d = Vector3d(z, y, x)

fun Vector3d.zyy(): Vector3d = Vector3d(z, y, y)

fun Vector3d.zyz(): Vector3d = Vector3d(z, y, z)

fun Vector3d.zzx(): Vector3d = Vector3d(z, z, x)

fun Vector3d.zzy(): Vector3d = Vector3d(z, z, y)

fun Vector3d.zzz(): Vector3d = Vector3d(z, z, z)

fun Vector4i.xx(): Vector2i = Vector2i(x, x)

fun Vector4i.xy(): Vector2i = Vector2i(x, y)

fun Vector4i.xz(): Vector2i = Vector2i(x, z)

fun Vector4i.xw(): Vector2i = Vector2i(x, w)

fun Vector4i.yx(): Vector2i = Vector2i(y, x)

fun Vector4i.yy(): Vector2i = Vector2i(y, y)

fun Vector4i.yz(): Vector2i = Vector2i(y, z)

fun Vector4i.yw(): Vector2i = Vector2i(y, w)

fun Vector4i.zx(): Vector2i = Vector2i(z, x)

fun Vector4i.zy(): Vector2i = Vector2i(z, y)

fun Vector4i.zz(): Vector2i = Vector2i(z, z)

fun Vector4i.zw(): Vector2i = Vector2i(z, w)

fun Vector4i.wx(): Vector2i = Vector2i(w, x)

fun Vector4i.wy(): Vector2i = Vector2i(w, y)

fun Vector4i.wz(): Vector2i = Vector2i(w, z)

fun Vector4i.ww(): Vector2i = Vector2i(w, w)

fun Vector4i.xxx(): Vector3i = Vector3i(x, x, x)

fun Vector4i.xxy(): Vector3i = Vector3i(x, x, y)

fun Vector4i.xxz(): Vector3i = Vector3i(x, x, z)

fun Vector4i.xxw(): Vector3i = Vector3i(x, x, w)

fun Vector4i.xyx(): Vector3i = Vector3i(x, y, x)

fun Vector4i.xyy(): Vector3i = Vector3i(x, y, y)

fun Vector4i.xyz(): Vector3i = Vector3i(x, y, z)

fun Vector4i.xyw(): Vector3i = Vector3i(x, y, w)

fun Vector4i.xzx(): Vector3i = Vector3i(x, z, x)

fun Vector4i.xzy(): Vector3i = Vector3i(x, z, y)

fun Vector4i.xzz(): Vector3i = Vector3i(x, z, z)

fun Vector4i.xzw(): Vector3i = Vector3i(x, z, w)

fun Vector4i.xwx(): Vector3i = Vector3i(x, w, x)

fun Vector4i.xwy(): Vector3i = Vector3i(x, w, y)

fun Vector4i.xwz(): Vector3i = Vector3i(x, w, z)

fun Vector4i.xww(): Vector3i = Vector3i(x, w, w)

fun Vector4i.yxx(): Vector3i = Vector3i(y, x, x)

fun Vector4i.yxy(): Vector3i = Vector3i(y, x, y)

fun Vector4i.yxz(): Vector3i = Vector3i(y, x, z)

fun Vector4i.yxw(): Vector3i = Vector3i(y, x, w)

fun Vector4i.yyx(): Vector3i = Vector3i(y, y, x)

fun Vector4i.yyy(): Vector3i = Vector3i(y, y, y)

fun Vector4i.yyz(): Vector3i = Vector3i(y, y, z)

fun Vector4i.yyw(): Vector3i = Vector3i(y, y, w)

fun Vector4i.yzx(): Vector3i = Vector3i(y, z, x)

fun Vector4i.yzy(): Vector3i = Vector3i(y, z, y)

fun Vector4i.yzz(): Vector3i = Vector3i(y, z, z)

fun Vector4i.yzw(): Vector3i = Vector3i(y, z, w)

fun Vector4i.ywx(): Vector3i = Vector3i(y, w, x)

fun Vector4i.ywy(): Vector3i = Vector3i(y, w, y)

fun Vector4i.ywz(): Vector3i = Vector3i(y, w, z)

fun Vector4i.yww(): Vector3i = Vector3i(y, w, w)

fun Vector4i.zxx(): Vector3i = Vector3i(z, x, x)

fun Vector4i.zxy(): Vector3i = Vector3i(z, x, y)

fun Vector4i.zxz(): Vector3i = Vector3i(z, x, z)

fun Vector4i.zxw(): Vector3i = Vector3i(z, x, w)

fun Vector4i.zyx(): Vector3i = Vector3i(z, y, x)

fun Vector4i.zyy(): Vector3i = Vector3i(z, y, y)

fun Vector4i.zyz(): Vector3i = Vector3i(z, y, z)

fun Vector4i.zyw(): Vector3i = Vector3i(z, y, w)

fun Vector4i.zzx(): Vector3i = Vector3i(z, z, x)

fun Vector4i.zzy(): Vector3i = Vector3i(z, z, y)

fun Vector4i.zzz(): Vector3i = Vector3i(z, z, z)

fun Vector4i.zzw(): Vector3i = Vector3i(z, z, w)

fun Vector4i.zwx(): Vector3i = Vector3i(z, w, x)

fun Vector4i.zwy(): Vector3i = Vector3i(z, w, y)

fun Vector4i.zwz(): Vector3i = Vector3i(z, w, z)

fun Vector4i.zww(): Vector3i = Vector3i(z, w, w)

fun Vector4i.wxx(): Vector3i = Vector3i(w, x, x)

fun Vector4i.wxy(): Vector3i = Vector3i(w, x, y)

fun Vector4i.wxz(): Vector3i = Vector3i(w, x, z)

fun Vector4i.wxw(): Vector3i = Vector3i(w, x, w)

fun Vector4i.wyx(): Vector3i = Vector3i(w, y, x)

fun Vector4i.wyy(): Vector3i = Vector3i(w, y, y)

fun Vector4i.wyz(): Vector3i = Vector3i(w, y, z)

fun Vector4i.wyw(): Vector3i = Vector3i(w, y, w)

fun Vector4i.wzx(): Vector3i = Vector3i(w, z, x)

fun Vector4i.wzy(): Vector3i = Vector3i(w, z, y)

fun Vector4i.wzz(): Vector3i = Vector3i(w, z, z)

fun Vector4i.wzw(): Vector3i = Vector3i(w, z, w)

fun Vector4i.wwx(): Vector3i = Vector3i(w, w, x)

fun Vector4i.wwy(): Vector3i = Vector3i(w, w, y)

fun Vector4i.wwz(): Vector3i = Vector3i(w, w, z)

fun Vector4i.www(): Vector3i = Vector3i(w, w, w)

fun Vector4i.xxxx(): Vector4i = Vector4i(x, x, x, x)

fun Vector4i.xxxy(): Vector4i = Vector4i(x, x, x, y)

fun Vector4i.xxxz(): Vector4i = Vector4i(x, x, x, z)

fun Vector4i.xxxw(): Vector4i = Vector4i(x, x, x, w)

fun Vector4i.xxyx(): Vector4i = Vector4i(x, x, y, x)

fun Vector4i.xxyy(): Vector4i = Vector4i(x, x, y, y)

fun Vector4i.xxyz(): Vector4i = Vector4i(x, x, y, z)

fun Vector4i.xxyw(): Vector4i = Vector4i(x, x, y, w)

fun Vector4i.xxzx(): Vector4i = Vector4i(x, x, z, x)

fun Vector4i.xxzy(): Vector4i = Vector4i(x, x, z, y)

fun Vector4i.xxzz(): Vector4i = Vector4i(x, x, z, z)

fun Vector4i.xxzw(): Vector4i = Vector4i(x, x, z, w)

fun Vector4i.xxwx(): Vector4i = Vector4i(x, x, w, x)

fun Vector4i.xxwy(): Vector4i = Vector4i(x, x, w, y)

fun Vector4i.xxwz(): Vector4i = Vector4i(x, x, w, z)

fun Vector4i.xxww(): Vector4i = Vector4i(x, x, w, w)

fun Vector4i.xyxx(): Vector4i = Vector4i(x, y, x, x)

fun Vector4i.xyxy(): Vector4i = Vector4i(x, y, x, y)

fun Vector4i.xyxz(): Vector4i = Vector4i(x, y, x, z)

fun Vector4i.xyxw(): Vector4i = Vector4i(x, y, x, w)

fun Vector4i.xyyx(): Vector4i = Vector4i(x, y, y, x)

fun Vector4i.xyyy(): Vector4i = Vector4i(x, y, y, y)

fun Vector4i.xyyz(): Vector4i = Vector4i(x, y, y, z)

fun Vector4i.xyyw(): Vector4i = Vector4i(x, y, y, w)

fun Vector4i.xyzx(): Vector4i = Vector4i(x, y, z, x)

fun Vector4i.xyzy(): Vector4i = Vector4i(x, y, z, y)

fun Vector4i.xyzz(): Vector4i = Vector4i(x, y, z, z)

fun Vector4i.xyzw(): Vector4i = Vector4i(x, y, z, w)

fun Vector4i.xywx(): Vector4i = Vector4i(x, y, w, x)

fun Vector4i.xywy(): Vector4i = Vector4i(x, y, w, y)

fun Vector4i.xywz(): Vector4i = Vector4i(x, y, w, z)

fun Vector4i.xyww(): Vector4i = Vector4i(x, y, w, w)

fun Vector4i.xzxx(): Vector4i = Vector4i(x, z, x, x)

fun Vector4i.xzxy(): Vector4i = Vector4i(x, z, x, y)

fun Vector4i.xzxz(): Vector4i = Vector4i(x, z, x, z)

fun Vector4i.xzxw(): Vector4i = Vector4i(x, z, x, w)

fun Vector4i.xzyx(): Vector4i = Vector4i(x, z, y, x)

fun Vector4i.xzyy(): Vector4i = Vector4i(x, z, y, y)

fun Vector4i.xzyz(): Vector4i = Vector4i(x, z, y, z)

fun Vector4i.xzyw(): Vector4i = Vector4i(x, z, y, w)

fun Vector4i.xzzx(): Vector4i = Vector4i(x, z, z, x)

fun Vector4i.xzzy(): Vector4i = Vector4i(x, z, z, y)

fun Vector4i.xzzz(): Vector4i = Vector4i(x, z, z, z)

fun Vector4i.xzzw(): Vector4i = Vector4i(x, z, z, w)

fun Vector4i.xzwx(): Vector4i = Vector4i(x, z, w, x)

fun Vector4i.xzwy(): Vector4i = Vector4i(x, z, w, y)

fun Vector4i.xzwz(): Vector4i = Vector4i(x, z, w, z)

fun Vector4i.xzww(): Vector4i = Vector4i(x, z, w, w)

fun Vector4i.xwxx(): Vector4i = Vector4i(x, w, x, x)

fun Vector4i.xwxy(): Vector4i = Vector4i(x, w, x, y)

fun Vector4i.xwxz(): Vector4i = Vector4i(x, w, x, z)

fun Vector4i.xwxw(): Vector4i = Vector4i(x, w, x, w)

fun Vector4i.xwyx(): Vector4i = Vector4i(x, w, y, x)

fun Vector4i.xwyy(): Vector4i = Vector4i(x, w, y, y)

fun Vector4i.xwyz(): Vector4i = Vector4i(x, w, y, z)

fun Vector4i.xwyw(): Vector4i = Vector4i(x, w, y, w)

fun Vector4i.xwzx(): Vector4i = Vector4i(x, w, z, x)

fun Vector4i.xwzy(): Vector4i = Vector4i(x, w, z, y)

fun Vector4i.xwzz(): Vector4i = Vector4i(x, w, z, z)

fun Vector4i.xwzw(): Vector4i = Vector4i(x, w, z, w)

fun Vector4i.xwwx(): Vector4i = Vector4i(x, w, w, x)

fun Vector4i.xwwy(): Vector4i = Vector4i(x, w, w, y)

fun Vector4i.xwwz(): Vector4i = Vector4i(x, w, w, z)

fun Vector4i.xwww(): Vector4i = Vector4i(x, w, w, w)

fun Vector4i.yxxx(): Vector4i = Vector4i(y, x, x, x)

fun Vector4i.yxxy(): Vector4i = Vector4i(y, x, x, y)

fun Vector4i.yxxz(): Vector4i = Vector4i(y, x, x, z)

fun Vector4i.yxxw(): Vector4i = Vector4i(y, x, x, w)

fun Vector4i.yxyx(): Vector4i = Vector4i(y, x, y, x)

fun Vector4i.yxyy(): Vector4i = Vector4i(y, x, y, y)

fun Vector4i.yxyz(): Vector4i = Vector4i(y, x, y, z)

fun Vector4i.yxyw(): Vector4i = Vector4i(y, x, y, w)

fun Vector4i.yxzx(): Vector4i = Vector4i(y, x, z, x)

fun Vector4i.yxzy(): Vector4i = Vector4i(y, x, z, y)

fun Vector4i.yxzz(): Vector4i = Vector4i(y, x, z, z)

fun Vector4i.yxzw(): Vector4i = Vector4i(y, x, z, w)

fun Vector4i.yxwx(): Vector4i = Vector4i(y, x, w, x)

fun Vector4i.yxwy(): Vector4i = Vector4i(y, x, w, y)

fun Vector4i.yxwz(): Vector4i = Vector4i(y, x, w, z)

fun Vector4i.yxww(): Vector4i = Vector4i(y, x, w, w)

fun Vector4i.yyxx(): Vector4i = Vector4i(y, y, x, x)

fun Vector4i.yyxy(): Vector4i = Vector4i(y, y, x, y)

fun Vector4i.yyxz(): Vector4i = Vector4i(y, y, x, z)

fun Vector4i.yyxw(): Vector4i = Vector4i(y, y, x, w)

fun Vector4i.yyyx(): Vector4i = Vector4i(y, y, y, x)

fun Vector4i.yyyy(): Vector4i = Vector4i(y, y, y, y)

fun Vector4i.yyyz(): Vector4i = Vector4i(y, y, y, z)

fun Vector4i.yyyw(): Vector4i = Vector4i(y, y, y, w)

fun Vector4i.yyzx(): Vector4i = Vector4i(y, y, z, x)

fun Vector4i.yyzy(): Vector4i = Vector4i(y, y, z, y)

fun Vector4i.yyzz(): Vector4i = Vector4i(y, y, z, z)

fun Vector4i.yyzw(): Vector4i = Vector4i(y, y, z, w)

fun Vector4i.yywx(): Vector4i = Vector4i(y, y, w, x)

fun Vector4i.yywy(): Vector4i = Vector4i(y, y, w, y)

fun Vector4i.yywz(): Vector4i = Vector4i(y, y, w, z)

fun Vector4i.yyww(): Vector4i = Vector4i(y, y, w, w)

fun Vector4i.yzxx(): Vector4i = Vector4i(y, z, x, x)

fun Vector4i.yzxy(): Vector4i = Vector4i(y, z, x, y)

fun Vector4i.yzxz(): Vector4i = Vector4i(y, z, x, z)

fun Vector4i.yzxw(): Vector4i = Vector4i(y, z, x, w)

fun Vector4i.yzyx(): Vector4i = Vector4i(y, z, y, x)

fun Vector4i.yzyy(): Vector4i = Vector4i(y, z, y, y)

fun Vector4i.yzyz(): Vector4i = Vector4i(y, z, y, z)

fun Vector4i.yzyw(): Vector4i = Vector4i(y, z, y, w)

fun Vector4i.yzzx(): Vector4i = Vector4i(y, z, z, x)

fun Vector4i.yzzy(): Vector4i = Vector4i(y, z, z, y)

fun Vector4i.yzzz(): Vector4i = Vector4i(y, z, z, z)

fun Vector4i.yzzw(): Vector4i = Vector4i(y, z, z, w)

fun Vector4i.yzwx(): Vector4i = Vector4i(y, z, w, x)

fun Vector4i.yzwy(): Vector4i = Vector4i(y, z, w, y)

fun Vector4i.yzwz(): Vector4i = Vector4i(y, z, w, z)

fun Vector4i.yzww(): Vector4i = Vector4i(y, z, w, w)

fun Vector4i.ywxx(): Vector4i = Vector4i(y, w, x, x)

fun Vector4i.ywxy(): Vector4i = Vector4i(y, w, x, y)

fun Vector4i.ywxz(): Vector4i = Vector4i(y, w, x, z)

fun Vector4i.ywxw(): Vector4i = Vector4i(y, w, x, w)

fun Vector4i.ywyx(): Vector4i = Vector4i(y, w, y, x)

fun Vector4i.ywyy(): Vector4i = Vector4i(y, w, y, y)

fun Vector4i.ywyz(): Vector4i = Vector4i(y, w, y, z)

fun Vector4i.ywyw(): Vector4i = Vector4i(y, w, y, w)

fun Vector4i.ywzx(): Vector4i = Vector4i(y, w, z, x)

fun Vector4i.ywzy(): Vector4i = Vector4i(y, w, z, y)

fun Vector4i.ywzz(): Vector4i = Vector4i(y, w, z, z)

fun Vector4i.ywzw(): Vector4i = Vector4i(y, w, z, w)

fun Vector4i.ywwx(): Vector4i = Vector4i(y, w, w, x)

fun Vector4i.ywwy(): Vector4i = Vector4i(y, w, w, y)

fun Vector4i.ywwz(): Vector4i = Vector4i(y, w, w, z)

fun Vector4i.ywww(): Vector4i = Vector4i(y, w, w, w)

fun Vector4i.zxxx(): Vector4i = Vector4i(z, x, x, x)

fun Vector4i.zxxy(): Vector4i = Vector4i(z, x, x, y)

fun Vector4i.zxxz(): Vector4i = Vector4i(z, x, x, z)

fun Vector4i.zxxw(): Vector4i = Vector4i(z, x, x, w)

fun Vector4i.zxyx(): Vector4i = Vector4i(z, x, y, x)

fun Vector4i.zxyy(): Vector4i = Vector4i(z, x, y, y)

fun Vector4i.zxyz(): Vector4i = Vector4i(z, x, y, z)

fun Vector4i.zxyw(): Vector4i = Vector4i(z, x, y, w)

fun Vector4i.zxzx(): Vector4i = Vector4i(z, x, z, x)

fun Vector4i.zxzy(): Vector4i = Vector4i(z, x, z, y)

fun Vector4i.zxzz(): Vector4i = Vector4i(z, x, z, z)

fun Vector4i.zxzw(): Vector4i = Vector4i(z, x, z, w)

fun Vector4i.zxwx(): Vector4i = Vector4i(z, x, w, x)

fun Vector4i.zxwy(): Vector4i = Vector4i(z, x, w, y)

fun Vector4i.zxwz(): Vector4i = Vector4i(z, x, w, z)

fun Vector4i.zxww(): Vector4i = Vector4i(z, x, w, w)

fun Vector4i.zyxx(): Vector4i = Vector4i(z, y, x, x)

fun Vector4i.zyxy(): Vector4i = Vector4i(z, y, x, y)

fun Vector4i.zyxz(): Vector4i = Vector4i(z, y, x, z)

fun Vector4i.zyxw(): Vector4i = Vector4i(z, y, x, w)

fun Vector4i.zyyx(): Vector4i = Vector4i(z, y, y, x)

fun Vector4i.zyyy(): Vector4i = Vector4i(z, y, y, y)

fun Vector4i.zyyz(): Vector4i = Vector4i(z, y, y, z)

fun Vector4i.zyyw(): Vector4i = Vector4i(z, y, y, w)

fun Vector4i.zyzx(): Vector4i = Vector4i(z, y, z, x)

fun Vector4i.zyzy(): Vector4i = Vector4i(z, y, z, y)

fun Vector4i.zyzz(): Vector4i = Vector4i(z, y, z, z)

fun Vector4i.zyzw(): Vector4i = Vector4i(z, y, z, w)

fun Vector4i.zywx(): Vector4i = Vector4i(z, y, w, x)

fun Vector4i.zywy(): Vector4i = Vector4i(z, y, w, y)

fun Vector4i.zywz(): Vector4i = Vector4i(z, y, w, z)

fun Vector4i.zyww(): Vector4i = Vector4i(z, y, w, w)

fun Vector4i.zzxx(): Vector4i = Vector4i(z, z, x, x)

fun Vector4i.zzxy(): Vector4i = Vector4i(z, z, x, y)

fun Vector4i.zzxz(): Vector4i = Vector4i(z, z, x, z)

fun Vector4i.zzxw(): Vector4i = Vector4i(z, z, x, w)

fun Vector4i.zzyx(): Vector4i = Vector4i(z, z, y, x)

fun Vector4i.zzyy(): Vector4i = Vector4i(z, z, y, y)

fun Vector4i.zzyz(): Vector4i = Vector4i(z, z, y, z)

fun Vector4i.zzyw(): Vector4i = Vector4i(z, z, y, w)

fun Vector4i.zzzx(): Vector4i = Vector4i(z, z, z, x)

fun Vector4i.zzzy(): Vector4i = Vector4i(z, z, z, y)

fun Vector4i.zzzz(): Vector4i = Vector4i(z, z, z, z)

fun Vector4i.zzzw(): Vector4i = Vector4i(z, z, z, w)

fun Vector4i.zzwx(): Vector4i = Vector4i(z, z, w, x)

fun Vector4i.zzwy(): Vector4i = Vector4i(z, z, w, y)

fun Vector4i.zzwz(): Vector4i = Vector4i(z, z, w, z)

fun Vector4i.zzww(): Vector4i = Vector4i(z, z, w, w)

fun Vector4i.zwxx(): Vector4i = Vector4i(z, w, x, x)

fun Vector4i.zwxy(): Vector4i = Vector4i(z, w, x, y)

fun Vector4i.zwxz(): Vector4i = Vector4i(z, w, x, z)

fun Vector4i.zwxw(): Vector4i = Vector4i(z, w, x, w)

fun Vector4i.zwyx(): Vector4i = Vector4i(z, w, y, x)

fun Vector4i.zwyy(): Vector4i = Vector4i(z, w, y, y)

fun Vector4i.zwyz(): Vector4i = Vector4i(z, w, y, z)

fun Vector4i.zwyw(): Vector4i = Vector4i(z, w, y, w)

fun Vector4i.zwzx(): Vector4i = Vector4i(z, w, z, x)

fun Vector4i.zwzy(): Vector4i = Vector4i(z, w, z, y)

fun Vector4i.zwzz(): Vector4i = Vector4i(z, w, z, z)

fun Vector4i.zwzw(): Vector4i = Vector4i(z, w, z, w)

fun Vector4i.zwwx(): Vector4i = Vector4i(z, w, w, x)

fun Vector4i.zwwy(): Vector4i = Vector4i(z, w, w, y)

fun Vector4i.zwwz(): Vector4i = Vector4i(z, w, w, z)

fun Vector4i.zwww(): Vector4i = Vector4i(z, w, w, w)

fun Vector4i.wxxx(): Vector4i = Vector4i(w, x, x, x)

fun Vector4i.wxxy(): Vector4i = Vector4i(w, x, x, y)

fun Vector4i.wxxz(): Vector4i = Vector4i(w, x, x, z)

fun Vector4i.wxxw(): Vector4i = Vector4i(w, x, x, w)

fun Vector4i.wxyx(): Vector4i = Vector4i(w, x, y, x)

fun Vector4i.wxyy(): Vector4i = Vector4i(w, x, y, y)

fun Vector4i.wxyz(): Vector4i = Vector4i(w, x, y, z)

fun Vector4i.wxyw(): Vector4i = Vector4i(w, x, y, w)

fun Vector4i.wxzx(): Vector4i = Vector4i(w, x, z, x)

fun Vector4i.wxzy(): Vector4i = Vector4i(w, x, z, y)

fun Vector4i.wxzz(): Vector4i = Vector4i(w, x, z, z)

fun Vector4i.wxzw(): Vector4i = Vector4i(w, x, z, w)

fun Vector4i.wxwx(): Vector4i = Vector4i(w, x, w, x)

fun Vector4i.wxwy(): Vector4i = Vector4i(w, x, w, y)

fun Vector4i.wxwz(): Vector4i = Vector4i(w, x, w, z)

fun Vector4i.wxww(): Vector4i = Vector4i(w, x, w, w)

fun Vector4i.wyxx(): Vector4i = Vector4i(w, y, x, x)

fun Vector4i.wyxy(): Vector4i = Vector4i(w, y, x, y)

fun Vector4i.wyxz(): Vector4i = Vector4i(w, y, x, z)

fun Vector4i.wyxw(): Vector4i = Vector4i(w, y, x, w)

fun Vector4i.wyyx(): Vector4i = Vector4i(w, y, y, x)

fun Vector4i.wyyy(): Vector4i = Vector4i(w, y, y, y)

fun Vector4i.wyyz(): Vector4i = Vector4i(w, y, y, z)

fun Vector4i.wyyw(): Vector4i = Vector4i(w, y, y, w)

fun Vector4i.wyzx(): Vector4i = Vector4i(w, y, z, x)

fun Vector4i.wyzy(): Vector4i = Vector4i(w, y, z, y)

fun Vector4i.wyzz(): Vector4i = Vector4i(w, y, z, z)

fun Vector4i.wyzw(): Vector4i = Vector4i(w, y, z, w)

fun Vector4i.wywx(): Vector4i = Vector4i(w, y, w, x)

fun Vector4i.wywy(): Vector4i = Vector4i(w, y, w, y)

fun Vector4i.wywz(): Vector4i = Vector4i(w, y, w, z)

fun Vector4i.wyww(): Vector4i = Vector4i(w, y, w, w)

fun Vector4i.wzxx(): Vector4i = Vector4i(w, z, x, x)

fun Vector4i.wzxy(): Vector4i = Vector4i(w, z, x, y)

fun Vector4i.wzxz(): Vector4i = Vector4i(w, z, x, z)

fun Vector4i.wzxw(): Vector4i = Vector4i(w, z, x, w)

fun Vector4i.wzyx(): Vector4i = Vector4i(w, z, y, x)

fun Vector4i.wzyy(): Vector4i = Vector4i(w, z, y, y)

fun Vector4i.wzyz(): Vector4i = Vector4i(w, z, y, z)

fun Vector4i.wzyw(): Vector4i = Vector4i(w, z, y, w)

fun Vector4i.wzzx(): Vector4i = Vector4i(w, z, z, x)

fun Vector4i.wzzy(): Vector4i = Vector4i(w, z, z, y)

fun Vector4i.wzzz(): Vector4i = Vector4i(w, z, z, z)

fun Vector4i.wzzw(): Vector4i = Vector4i(w, z, z, w)

fun Vector4i.wzwx(): Vector4i = Vector4i(w, z, w, x)

fun Vector4i.wzwy(): Vector4i = Vector4i(w, z, w, y)

fun Vector4i.wzwz(): Vector4i = Vector4i(w, z, w, z)

fun Vector4i.wzww(): Vector4i = Vector4i(w, z, w, w)

fun Vector4i.wwxx(): Vector4i = Vector4i(w, w, x, x)

fun Vector4i.wwxy(): Vector4i = Vector4i(w, w, x, y)

fun Vector4i.wwxz(): Vector4i = Vector4i(w, w, x, z)

fun Vector4i.wwxw(): Vector4i = Vector4i(w, w, x, w)

fun Vector4i.wwyx(): Vector4i = Vector4i(w, w, y, x)

fun Vector4i.wwyy(): Vector4i = Vector4i(w, w, y, y)

fun Vector4i.wwyz(): Vector4i = Vector4i(w, w, y, z)

fun Vector4i.wwyw(): Vector4i = Vector4i(w, w, y, w)

fun Vector4i.wwzx(): Vector4i = Vector4i(w, w, z, x)

fun Vector4i.wwzy(): Vector4i = Vector4i(w, w, z, y)

fun Vector4i.wwzz(): Vector4i = Vector4i(w, w, z, z)

fun Vector4i.wwzw(): Vector4i = Vector4i(w, w, z, w)

fun Vector4i.wwwx(): Vector4i = Vector4i(w, w, w, x)

fun Vector4i.wwwy(): Vector4i = Vector4i(w, w, w, y)

fun Vector4i.wwwz(): Vector4i = Vector4i(w, w, w, z)

fun Vector4i.wwww(): Vector4i = Vector4i(w, w, w, w)

fun Vector4f.xx(): Vector2f = Vector2f(x, x)

fun Vector4f.xy(): Vector2f = Vector2f(x, y)

fun Vector4f.xz(): Vector2f = Vector2f(x, z)

fun Vector4f.xw(): Vector2f = Vector2f(x, w)

fun Vector4f.yx(): Vector2f = Vector2f(y, x)

fun Vector4f.yy(): Vector2f = Vector2f(y, y)

fun Vector4f.yz(): Vector2f = Vector2f(y, z)

fun Vector4f.yw(): Vector2f = Vector2f(y, w)

fun Vector4f.zx(): Vector2f = Vector2f(z, x)

fun Vector4f.zy(): Vector2f = Vector2f(z, y)

fun Vector4f.zz(): Vector2f = Vector2f(z, z)

fun Vector4f.zw(): Vector2f = Vector2f(z, w)

fun Vector4f.wx(): Vector2f = Vector2f(w, x)

fun Vector4f.wy(): Vector2f = Vector2f(w, y)

fun Vector4f.wz(): Vector2f = Vector2f(w, z)

fun Vector4f.ww(): Vector2f = Vector2f(w, w)

fun Vector4f.xxx(): Vector3f = Vector3f(x, x, x)

fun Vector4f.xxy(): Vector3f = Vector3f(x, x, y)

fun Vector4f.xxz(): Vector3f = Vector3f(x, x, z)

fun Vector4f.xxw(): Vector3f = Vector3f(x, x, w)

fun Vector4f.xyx(): Vector3f = Vector3f(x, y, x)

fun Vector4f.xyy(): Vector3f = Vector3f(x, y, y)

fun Vector4f.xyz(): Vector3f = Vector3f(x, y, z)

fun Vector4f.xyw(): Vector3f = Vector3f(x, y, w)

fun Vector4f.xzx(): Vector3f = Vector3f(x, z, x)

fun Vector4f.xzy(): Vector3f = Vector3f(x, z, y)

fun Vector4f.xzz(): Vector3f = Vector3f(x, z, z)

fun Vector4f.xzw(): Vector3f = Vector3f(x, z, w)

fun Vector4f.xwx(): Vector3f = Vector3f(x, w, x)

fun Vector4f.xwy(): Vector3f = Vector3f(x, w, y)

fun Vector4f.xwz(): Vector3f = Vector3f(x, w, z)

fun Vector4f.xww(): Vector3f = Vector3f(x, w, w)

fun Vector4f.yxx(): Vector3f = Vector3f(y, x, x)

fun Vector4f.yxy(): Vector3f = Vector3f(y, x, y)

fun Vector4f.yxz(): Vector3f = Vector3f(y, x, z)

fun Vector4f.yxw(): Vector3f = Vector3f(y, x, w)

fun Vector4f.yyx(): Vector3f = Vector3f(y, y, x)

fun Vector4f.yyy(): Vector3f = Vector3f(y, y, y)

fun Vector4f.yyz(): Vector3f = Vector3f(y, y, z)

fun Vector4f.yyw(): Vector3f = Vector3f(y, y, w)

fun Vector4f.yzx(): Vector3f = Vector3f(y, z, x)

fun Vector4f.yzy(): Vector3f = Vector3f(y, z, y)

fun Vector4f.yzz(): Vector3f = Vector3f(y, z, z)

fun Vector4f.yzw(): Vector3f = Vector3f(y, z, w)

fun Vector4f.ywx(): Vector3f = Vector3f(y, w, x)

fun Vector4f.ywy(): Vector3f = Vector3f(y, w, y)

fun Vector4f.ywz(): Vector3f = Vector3f(y, w, z)

fun Vector4f.yww(): Vector3f = Vector3f(y, w, w)

fun Vector4f.zxx(): Vector3f = Vector3f(z, x, x)

fun Vector4f.zxy(): Vector3f = Vector3f(z, x, y)

fun Vector4f.zxz(): Vector3f = Vector3f(z, x, z)

fun Vector4f.zxw(): Vector3f = Vector3f(z, x, w)

fun Vector4f.zyx(): Vector3f = Vector3f(z, y, x)

fun Vector4f.zyy(): Vector3f = Vector3f(z, y, y)

fun Vector4f.zyz(): Vector3f = Vector3f(z, y, z)

fun Vector4f.zyw(): Vector3f = Vector3f(z, y, w)

fun Vector4f.zzx(): Vector3f = Vector3f(z, z, x)

fun Vector4f.zzy(): Vector3f = Vector3f(z, z, y)

fun Vector4f.zzz(): Vector3f = Vector3f(z, z, z)

fun Vector4f.zzw(): Vector3f = Vector3f(z, z, w)

fun Vector4f.zwx(): Vector3f = Vector3f(z, w, x)

fun Vector4f.zwy(): Vector3f = Vector3f(z, w, y)

fun Vector4f.zwz(): Vector3f = Vector3f(z, w, z)

fun Vector4f.zww(): Vector3f = Vector3f(z, w, w)

fun Vector4f.wxx(): Vector3f = Vector3f(w, x, x)

fun Vector4f.wxy(): Vector3f = Vector3f(w, x, y)

fun Vector4f.wxz(): Vector3f = Vector3f(w, x, z)

fun Vector4f.wxw(): Vector3f = Vector3f(w, x, w)

fun Vector4f.wyx(): Vector3f = Vector3f(w, y, x)

fun Vector4f.wyy(): Vector3f = Vector3f(w, y, y)

fun Vector4f.wyz(): Vector3f = Vector3f(w, y, z)

fun Vector4f.wyw(): Vector3f = Vector3f(w, y, w)

fun Vector4f.wzx(): Vector3f = Vector3f(w, z, x)

fun Vector4f.wzy(): Vector3f = Vector3f(w, z, y)

fun Vector4f.wzz(): Vector3f = Vector3f(w, z, z)

fun Vector4f.wzw(): Vector3f = Vector3f(w, z, w)

fun Vector4f.wwx(): Vector3f = Vector3f(w, w, x)

fun Vector4f.wwy(): Vector3f = Vector3f(w, w, y)

fun Vector4f.wwz(): Vector3f = Vector3f(w, w, z)

fun Vector4f.www(): Vector3f = Vector3f(w, w, w)

fun Vector4f.xxxx(): Vector4f = Vector4f(x, x, x, x)

fun Vector4f.xxxy(): Vector4f = Vector4f(x, x, x, y)

fun Vector4f.xxxz(): Vector4f = Vector4f(x, x, x, z)

fun Vector4f.xxxw(): Vector4f = Vector4f(x, x, x, w)

fun Vector4f.xxyx(): Vector4f = Vector4f(x, x, y, x)

fun Vector4f.xxyy(): Vector4f = Vector4f(x, x, y, y)

fun Vector4f.xxyz(): Vector4f = Vector4f(x, x, y, z)

fun Vector4f.xxyw(): Vector4f = Vector4f(x, x, y, w)

fun Vector4f.xxzx(): Vector4f = Vector4f(x, x, z, x)

fun Vector4f.xxzy(): Vector4f = Vector4f(x, x, z, y)

fun Vector4f.xxzz(): Vector4f = Vector4f(x, x, z, z)

fun Vector4f.xxzw(): Vector4f = Vector4f(x, x, z, w)

fun Vector4f.xxwx(): Vector4f = Vector4f(x, x, w, x)

fun Vector4f.xxwy(): Vector4f = Vector4f(x, x, w, y)

fun Vector4f.xxwz(): Vector4f = Vector4f(x, x, w, z)

fun Vector4f.xxww(): Vector4f = Vector4f(x, x, w, w)

fun Vector4f.xyxx(): Vector4f = Vector4f(x, y, x, x)

fun Vector4f.xyxy(): Vector4f = Vector4f(x, y, x, y)

fun Vector4f.xyxz(): Vector4f = Vector4f(x, y, x, z)

fun Vector4f.xyxw(): Vector4f = Vector4f(x, y, x, w)

fun Vector4f.xyyx(): Vector4f = Vector4f(x, y, y, x)

fun Vector4f.xyyy(): Vector4f = Vector4f(x, y, y, y)

fun Vector4f.xyyz(): Vector4f = Vector4f(x, y, y, z)

fun Vector4f.xyyw(): Vector4f = Vector4f(x, y, y, w)

fun Vector4f.xyzx(): Vector4f = Vector4f(x, y, z, x)

fun Vector4f.xyzy(): Vector4f = Vector4f(x, y, z, y)

fun Vector4f.xyzz(): Vector4f = Vector4f(x, y, z, z)

fun Vector4f.xyzw(): Vector4f = Vector4f(x, y, z, w)

fun Vector4f.xywx(): Vector4f = Vector4f(x, y, w, x)

fun Vector4f.xywy(): Vector4f = Vector4f(x, y, w, y)

fun Vector4f.xywz(): Vector4f = Vector4f(x, y, w, z)

fun Vector4f.xyww(): Vector4f = Vector4f(x, y, w, w)

fun Vector4f.xzxx(): Vector4f = Vector4f(x, z, x, x)

fun Vector4f.xzxy(): Vector4f = Vector4f(x, z, x, y)

fun Vector4f.xzxz(): Vector4f = Vector4f(x, z, x, z)

fun Vector4f.xzxw(): Vector4f = Vector4f(x, z, x, w)

fun Vector4f.xzyx(): Vector4f = Vector4f(x, z, y, x)

fun Vector4f.xzyy(): Vector4f = Vector4f(x, z, y, y)

fun Vector4f.xzyz(): Vector4f = Vector4f(x, z, y, z)

fun Vector4f.xzyw(): Vector4f = Vector4f(x, z, y, w)

fun Vector4f.xzzx(): Vector4f = Vector4f(x, z, z, x)

fun Vector4f.xzzy(): Vector4f = Vector4f(x, z, z, y)

fun Vector4f.xzzz(): Vector4f = Vector4f(x, z, z, z)

fun Vector4f.xzzw(): Vector4f = Vector4f(x, z, z, w)

fun Vector4f.xzwx(): Vector4f = Vector4f(x, z, w, x)

fun Vector4f.xzwy(): Vector4f = Vector4f(x, z, w, y)

fun Vector4f.xzwz(): Vector4f = Vector4f(x, z, w, z)

fun Vector4f.xzww(): Vector4f = Vector4f(x, z, w, w)

fun Vector4f.xwxx(): Vector4f = Vector4f(x, w, x, x)

fun Vector4f.xwxy(): Vector4f = Vector4f(x, w, x, y)

fun Vector4f.xwxz(): Vector4f = Vector4f(x, w, x, z)

fun Vector4f.xwxw(): Vector4f = Vector4f(x, w, x, w)

fun Vector4f.xwyx(): Vector4f = Vector4f(x, w, y, x)

fun Vector4f.xwyy(): Vector4f = Vector4f(x, w, y, y)

fun Vector4f.xwyz(): Vector4f = Vector4f(x, w, y, z)

fun Vector4f.xwyw(): Vector4f = Vector4f(x, w, y, w)

fun Vector4f.xwzx(): Vector4f = Vector4f(x, w, z, x)

fun Vector4f.xwzy(): Vector4f = Vector4f(x, w, z, y)

fun Vector4f.xwzz(): Vector4f = Vector4f(x, w, z, z)

fun Vector4f.xwzw(): Vector4f = Vector4f(x, w, z, w)

fun Vector4f.xwwx(): Vector4f = Vector4f(x, w, w, x)

fun Vector4f.xwwy(): Vector4f = Vector4f(x, w, w, y)

fun Vector4f.xwwz(): Vector4f = Vector4f(x, w, w, z)

fun Vector4f.xwww(): Vector4f = Vector4f(x, w, w, w)

fun Vector4f.yxxx(): Vector4f = Vector4f(y, x, x, x)

fun Vector4f.yxxy(): Vector4f = Vector4f(y, x, x, y)

fun Vector4f.yxxz(): Vector4f = Vector4f(y, x, x, z)

fun Vector4f.yxxw(): Vector4f = Vector4f(y, x, x, w)

fun Vector4f.yxyx(): Vector4f = Vector4f(y, x, y, x)

fun Vector4f.yxyy(): Vector4f = Vector4f(y, x, y, y)

fun Vector4f.yxyz(): Vector4f = Vector4f(y, x, y, z)

fun Vector4f.yxyw(): Vector4f = Vector4f(y, x, y, w)

fun Vector4f.yxzx(): Vector4f = Vector4f(y, x, z, x)

fun Vector4f.yxzy(): Vector4f = Vector4f(y, x, z, y)

fun Vector4f.yxzz(): Vector4f = Vector4f(y, x, z, z)

fun Vector4f.yxzw(): Vector4f = Vector4f(y, x, z, w)

fun Vector4f.yxwx(): Vector4f = Vector4f(y, x, w, x)

fun Vector4f.yxwy(): Vector4f = Vector4f(y, x, w, y)

fun Vector4f.yxwz(): Vector4f = Vector4f(y, x, w, z)

fun Vector4f.yxww(): Vector4f = Vector4f(y, x, w, w)

fun Vector4f.yyxx(): Vector4f = Vector4f(y, y, x, x)

fun Vector4f.yyxy(): Vector4f = Vector4f(y, y, x, y)

fun Vector4f.yyxz(): Vector4f = Vector4f(y, y, x, z)

fun Vector4f.yyxw(): Vector4f = Vector4f(y, y, x, w)

fun Vector4f.yyyx(): Vector4f = Vector4f(y, y, y, x)

fun Vector4f.yyyy(): Vector4f = Vector4f(y, y, y, y)

fun Vector4f.yyyz(): Vector4f = Vector4f(y, y, y, z)

fun Vector4f.yyyw(): Vector4f = Vector4f(y, y, y, w)

fun Vector4f.yyzx(): Vector4f = Vector4f(y, y, z, x)

fun Vector4f.yyzy(): Vector4f = Vector4f(y, y, z, y)

fun Vector4f.yyzz(): Vector4f = Vector4f(y, y, z, z)

fun Vector4f.yyzw(): Vector4f = Vector4f(y, y, z, w)

fun Vector4f.yywx(): Vector4f = Vector4f(y, y, w, x)

fun Vector4f.yywy(): Vector4f = Vector4f(y, y, w, y)

fun Vector4f.yywz(): Vector4f = Vector4f(y, y, w, z)

fun Vector4f.yyww(): Vector4f = Vector4f(y, y, w, w)

fun Vector4f.yzxx(): Vector4f = Vector4f(y, z, x, x)

fun Vector4f.yzxy(): Vector4f = Vector4f(y, z, x, y)

fun Vector4f.yzxz(): Vector4f = Vector4f(y, z, x, z)

fun Vector4f.yzxw(): Vector4f = Vector4f(y, z, x, w)

fun Vector4f.yzyx(): Vector4f = Vector4f(y, z, y, x)

fun Vector4f.yzyy(): Vector4f = Vector4f(y, z, y, y)

fun Vector4f.yzyz(): Vector4f = Vector4f(y, z, y, z)

fun Vector4f.yzyw(): Vector4f = Vector4f(y, z, y, w)

fun Vector4f.yzzx(): Vector4f = Vector4f(y, z, z, x)

fun Vector4f.yzzy(): Vector4f = Vector4f(y, z, z, y)

fun Vector4f.yzzz(): Vector4f = Vector4f(y, z, z, z)

fun Vector4f.yzzw(): Vector4f = Vector4f(y, z, z, w)

fun Vector4f.yzwx(): Vector4f = Vector4f(y, z, w, x)

fun Vector4f.yzwy(): Vector4f = Vector4f(y, z, w, y)

fun Vector4f.yzwz(): Vector4f = Vector4f(y, z, w, z)

fun Vector4f.yzww(): Vector4f = Vector4f(y, z, w, w)

fun Vector4f.ywxx(): Vector4f = Vector4f(y, w, x, x)

fun Vector4f.ywxy(): Vector4f = Vector4f(y, w, x, y)

fun Vector4f.ywxz(): Vector4f = Vector4f(y, w, x, z)

fun Vector4f.ywxw(): Vector4f = Vector4f(y, w, x, w)

fun Vector4f.ywyx(): Vector4f = Vector4f(y, w, y, x)

fun Vector4f.ywyy(): Vector4f = Vector4f(y, w, y, y)

fun Vector4f.ywyz(): Vector4f = Vector4f(y, w, y, z)

fun Vector4f.ywyw(): Vector4f = Vector4f(y, w, y, w)

fun Vector4f.ywzx(): Vector4f = Vector4f(y, w, z, x)

fun Vector4f.ywzy(): Vector4f = Vector4f(y, w, z, y)

fun Vector4f.ywzz(): Vector4f = Vector4f(y, w, z, z)

fun Vector4f.ywzw(): Vector4f = Vector4f(y, w, z, w)

fun Vector4f.ywwx(): Vector4f = Vector4f(y, w, w, x)

fun Vector4f.ywwy(): Vector4f = Vector4f(y, w, w, y)

fun Vector4f.ywwz(): Vector4f = Vector4f(y, w, w, z)

fun Vector4f.ywww(): Vector4f = Vector4f(y, w, w, w)

fun Vector4f.zxxx(): Vector4f = Vector4f(z, x, x, x)

fun Vector4f.zxxy(): Vector4f = Vector4f(z, x, x, y)

fun Vector4f.zxxz(): Vector4f = Vector4f(z, x, x, z)

fun Vector4f.zxxw(): Vector4f = Vector4f(z, x, x, w)

fun Vector4f.zxyx(): Vector4f = Vector4f(z, x, y, x)

fun Vector4f.zxyy(): Vector4f = Vector4f(z, x, y, y)

fun Vector4f.zxyz(): Vector4f = Vector4f(z, x, y, z)

fun Vector4f.zxyw(): Vector4f = Vector4f(z, x, y, w)

fun Vector4f.zxzx(): Vector4f = Vector4f(z, x, z, x)

fun Vector4f.zxzy(): Vector4f = Vector4f(z, x, z, y)

fun Vector4f.zxzz(): Vector4f = Vector4f(z, x, z, z)

fun Vector4f.zxzw(): Vector4f = Vector4f(z, x, z, w)

fun Vector4f.zxwx(): Vector4f = Vector4f(z, x, w, x)

fun Vector4f.zxwy(): Vector4f = Vector4f(z, x, w, y)

fun Vector4f.zxwz(): Vector4f = Vector4f(z, x, w, z)

fun Vector4f.zxww(): Vector4f = Vector4f(z, x, w, w)

fun Vector4f.zyxx(): Vector4f = Vector4f(z, y, x, x)

fun Vector4f.zyxy(): Vector4f = Vector4f(z, y, x, y)

fun Vector4f.zyxz(): Vector4f = Vector4f(z, y, x, z)

fun Vector4f.zyxw(): Vector4f = Vector4f(z, y, x, w)

fun Vector4f.zyyx(): Vector4f = Vector4f(z, y, y, x)

fun Vector4f.zyyy(): Vector4f = Vector4f(z, y, y, y)

fun Vector4f.zyyz(): Vector4f = Vector4f(z, y, y, z)

fun Vector4f.zyyw(): Vector4f = Vector4f(z, y, y, w)

fun Vector4f.zyzx(): Vector4f = Vector4f(z, y, z, x)

fun Vector4f.zyzy(): Vector4f = Vector4f(z, y, z, y)

fun Vector4f.zyzz(): Vector4f = Vector4f(z, y, z, z)

fun Vector4f.zyzw(): Vector4f = Vector4f(z, y, z, w)

fun Vector4f.zywx(): Vector4f = Vector4f(z, y, w, x)

fun Vector4f.zywy(): Vector4f = Vector4f(z, y, w, y)

fun Vector4f.zywz(): Vector4f = Vector4f(z, y, w, z)

fun Vector4f.zyww(): Vector4f = Vector4f(z, y, w, w)

fun Vector4f.zzxx(): Vector4f = Vector4f(z, z, x, x)

fun Vector4f.zzxy(): Vector4f = Vector4f(z, z, x, y)

fun Vector4f.zzxz(): Vector4f = Vector4f(z, z, x, z)

fun Vector4f.zzxw(): Vector4f = Vector4f(z, z, x, w)

fun Vector4f.zzyx(): Vector4f = Vector4f(z, z, y, x)

fun Vector4f.zzyy(): Vector4f = Vector4f(z, z, y, y)

fun Vector4f.zzyz(): Vector4f = Vector4f(z, z, y, z)

fun Vector4f.zzyw(): Vector4f = Vector4f(z, z, y, w)

fun Vector4f.zzzx(): Vector4f = Vector4f(z, z, z, x)

fun Vector4f.zzzy(): Vector4f = Vector4f(z, z, z, y)

fun Vector4f.zzzz(): Vector4f = Vector4f(z, z, z, z)

fun Vector4f.zzzw(): Vector4f = Vector4f(z, z, z, w)

fun Vector4f.zzwx(): Vector4f = Vector4f(z, z, w, x)

fun Vector4f.zzwy(): Vector4f = Vector4f(z, z, w, y)

fun Vector4f.zzwz(): Vector4f = Vector4f(z, z, w, z)

fun Vector4f.zzww(): Vector4f = Vector4f(z, z, w, w)

fun Vector4f.zwxx(): Vector4f = Vector4f(z, w, x, x)

fun Vector4f.zwxy(): Vector4f = Vector4f(z, w, x, y)

fun Vector4f.zwxz(): Vector4f = Vector4f(z, w, x, z)

fun Vector4f.zwxw(): Vector4f = Vector4f(z, w, x, w)

fun Vector4f.zwyx(): Vector4f = Vector4f(z, w, y, x)

fun Vector4f.zwyy(): Vector4f = Vector4f(z, w, y, y)

fun Vector4f.zwyz(): Vector4f = Vector4f(z, w, y, z)

fun Vector4f.zwyw(): Vector4f = Vector4f(z, w, y, w)

fun Vector4f.zwzx(): Vector4f = Vector4f(z, w, z, x)

fun Vector4f.zwzy(): Vector4f = Vector4f(z, w, z, y)

fun Vector4f.zwzz(): Vector4f = Vector4f(z, w, z, z)

fun Vector4f.zwzw(): Vector4f = Vector4f(z, w, z, w)

fun Vector4f.zwwx(): Vector4f = Vector4f(z, w, w, x)

fun Vector4f.zwwy(): Vector4f = Vector4f(z, w, w, y)

fun Vector4f.zwwz(): Vector4f = Vector4f(z, w, w, z)

fun Vector4f.zwww(): Vector4f = Vector4f(z, w, w, w)

fun Vector4f.wxxx(): Vector4f = Vector4f(w, x, x, x)

fun Vector4f.wxxy(): Vector4f = Vector4f(w, x, x, y)

fun Vector4f.wxxz(): Vector4f = Vector4f(w, x, x, z)

fun Vector4f.wxxw(): Vector4f = Vector4f(w, x, x, w)

fun Vector4f.wxyx(): Vector4f = Vector4f(w, x, y, x)

fun Vector4f.wxyy(): Vector4f = Vector4f(w, x, y, y)

fun Vector4f.wxyz(): Vector4f = Vector4f(w, x, y, z)

fun Vector4f.wxyw(): Vector4f = Vector4f(w, x, y, w)

fun Vector4f.wxzx(): Vector4f = Vector4f(w, x, z, x)

fun Vector4f.wxzy(): Vector4f = Vector4f(w, x, z, y)

fun Vector4f.wxzz(): Vector4f = Vector4f(w, x, z, z)

fun Vector4f.wxzw(): Vector4f = Vector4f(w, x, z, w)

fun Vector4f.wxwx(): Vector4f = Vector4f(w, x, w, x)

fun Vector4f.wxwy(): Vector4f = Vector4f(w, x, w, y)

fun Vector4f.wxwz(): Vector4f = Vector4f(w, x, w, z)

fun Vector4f.wxww(): Vector4f = Vector4f(w, x, w, w)

fun Vector4f.wyxx(): Vector4f = Vector4f(w, y, x, x)

fun Vector4f.wyxy(): Vector4f = Vector4f(w, y, x, y)

fun Vector4f.wyxz(): Vector4f = Vector4f(w, y, x, z)

fun Vector4f.wyxw(): Vector4f = Vector4f(w, y, x, w)

fun Vector4f.wyyx(): Vector4f = Vector4f(w, y, y, x)

fun Vector4f.wyyy(): Vector4f = Vector4f(w, y, y, y)

fun Vector4f.wyyz(): Vector4f = Vector4f(w, y, y, z)

fun Vector4f.wyyw(): Vector4f = Vector4f(w, y, y, w)

fun Vector4f.wyzx(): Vector4f = Vector4f(w, y, z, x)

fun Vector4f.wyzy(): Vector4f = Vector4f(w, y, z, y)

fun Vector4f.wyzz(): Vector4f = Vector4f(w, y, z, z)

fun Vector4f.wyzw(): Vector4f = Vector4f(w, y, z, w)

fun Vector4f.wywx(): Vector4f = Vector4f(w, y, w, x)

fun Vector4f.wywy(): Vector4f = Vector4f(w, y, w, y)

fun Vector4f.wywz(): Vector4f = Vector4f(w, y, w, z)

fun Vector4f.wyww(): Vector4f = Vector4f(w, y, w, w)

fun Vector4f.wzxx(): Vector4f = Vector4f(w, z, x, x)

fun Vector4f.wzxy(): Vector4f = Vector4f(w, z, x, y)

fun Vector4f.wzxz(): Vector4f = Vector4f(w, z, x, z)

fun Vector4f.wzxw(): Vector4f = Vector4f(w, z, x, w)

fun Vector4f.wzyx(): Vector4f = Vector4f(w, z, y, x)

fun Vector4f.wzyy(): Vector4f = Vector4f(w, z, y, y)

fun Vector4f.wzyz(): Vector4f = Vector4f(w, z, y, z)

fun Vector4f.wzyw(): Vector4f = Vector4f(w, z, y, w)

fun Vector4f.wzzx(): Vector4f = Vector4f(w, z, z, x)

fun Vector4f.wzzy(): Vector4f = Vector4f(w, z, z, y)

fun Vector4f.wzzz(): Vector4f = Vector4f(w, z, z, z)

fun Vector4f.wzzw(): Vector4f = Vector4f(w, z, z, w)

fun Vector4f.wzwx(): Vector4f = Vector4f(w, z, w, x)

fun Vector4f.wzwy(): Vector4f = Vector4f(w, z, w, y)

fun Vector4f.wzwz(): Vector4f = Vector4f(w, z, w, z)

fun Vector4f.wzww(): Vector4f = Vector4f(w, z, w, w)

fun Vector4f.wwxx(): Vector4f = Vector4f(w, w, x, x)

fun Vector4f.wwxy(): Vector4f = Vector4f(w, w, x, y)

fun Vector4f.wwxz(): Vector4f = Vector4f(w, w, x, z)

fun Vector4f.wwxw(): Vector4f = Vector4f(w, w, x, w)

fun Vector4f.wwyx(): Vector4f = Vector4f(w, w, y, x)

fun Vector4f.wwyy(): Vector4f = Vector4f(w, w, y, y)

fun Vector4f.wwyz(): Vector4f = Vector4f(w, w, y, z)

fun Vector4f.wwyw(): Vector4f = Vector4f(w, w, y, w)

fun Vector4f.wwzx(): Vector4f = Vector4f(w, w, z, x)

fun Vector4f.wwzy(): Vector4f = Vector4f(w, w, z, y)

fun Vector4f.wwzz(): Vector4f = Vector4f(w, w, z, z)

fun Vector4f.wwzw(): Vector4f = Vector4f(w, w, z, w)

fun Vector4f.wwwx(): Vector4f = Vector4f(w, w, w, x)

fun Vector4f.wwwy(): Vector4f = Vector4f(w, w, w, y)

fun Vector4f.wwwz(): Vector4f = Vector4f(w, w, w, z)

fun Vector4f.wwww(): Vector4f = Vector4f(w, w, w, w)

fun Vector4d.xx(): Vector2f = Vector2f(x.toFloat(), x.toFloat())

fun Vector4d.xy(): Vector2f = Vector2f(x.toFloat(), y.toFloat())

fun Vector4d.xz(): Vector2f = Vector2f(x.toFloat(), z.toFloat())

fun Vector4d.xw(): Vector2f = Vector2f(x.toFloat(), w.toFloat())

fun Vector4d.yx(): Vector2f = Vector2f(y.toFloat(), x.toFloat())

fun Vector4d.yy(): Vector2f = Vector2f(y.toFloat(), y.toFloat())

fun Vector4d.yz(): Vector2f = Vector2f(y.toFloat(), z.toFloat())

fun Vector4d.yw(): Vector2f = Vector2f(y.toFloat(), w.toFloat())

fun Vector4d.zx(): Vector2f = Vector2f(z.toFloat(), x.toFloat())

fun Vector4d.zy(): Vector2f = Vector2f(z.toFloat(), y.toFloat())

fun Vector4d.zz(): Vector2f = Vector2f(z.toFloat(), z.toFloat())

fun Vector4d.zw(): Vector2f = Vector2f(z.toFloat(), w.toFloat())

fun Vector4d.wx(): Vector2f = Vector2f(w.toFloat(), x.toFloat())

fun Vector4d.wy(): Vector2f = Vector2f(w.toFloat(), y.toFloat())

fun Vector4d.wz(): Vector2f = Vector2f(w.toFloat(), z.toFloat())

fun Vector4d.ww(): Vector2f = Vector2f(w.toFloat(), w.toFloat())

fun Vector4d.xxx(): Vector3d = Vector3d(x, x, x)

fun Vector4d.xxy(): Vector3d = Vector3d(x, x, y)

fun Vector4d.xxz(): Vector3d = Vector3d(x, x, z)

fun Vector4d.xxw(): Vector3d = Vector3d(x, x, w)

fun Vector4d.xyx(): Vector3d = Vector3d(x, y, x)

fun Vector4d.xyy(): Vector3d = Vector3d(x, y, y)

fun Vector4d.xyz(): Vector3d = Vector3d(x, y, z)

fun Vector4d.xyw(): Vector3d = Vector3d(x, y, w)

fun Vector4d.xzx(): Vector3d = Vector3d(x, z, x)

fun Vector4d.xzy(): Vector3d = Vector3d(x, z, y)

fun Vector4d.xzz(): Vector3d = Vector3d(x, z, z)

fun Vector4d.xzw(): Vector3d = Vector3d(x, z, w)

fun Vector4d.xwx(): Vector3d = Vector3d(x, w, x)

fun Vector4d.xwy(): Vector3d = Vector3d(x, w, y)

fun Vector4d.xwz(): Vector3d = Vector3d(x, w, z)

fun Vector4d.xww(): Vector3d = Vector3d(x, w, w)

fun Vector4d.yxx(): Vector3d = Vector3d(y, x, x)

fun Vector4d.yxy(): Vector3d = Vector3d(y, x, y)

fun Vector4d.yxz(): Vector3d = Vector3d(y, x, z)

fun Vector4d.yxw(): Vector3d = Vector3d(y, x, w)

fun Vector4d.yyx(): Vector3d = Vector3d(y, y, x)

fun Vector4d.yyy(): Vector3d = Vector3d(y, y, y)

fun Vector4d.yyz(): Vector3d = Vector3d(y, y, z)

fun Vector4d.yyw(): Vector3d = Vector3d(y, y, w)

fun Vector4d.yzx(): Vector3d = Vector3d(y, z, x)

fun Vector4d.yzy(): Vector3d = Vector3d(y, z, y)

fun Vector4d.yzz(): Vector3d = Vector3d(y, z, z)

fun Vector4d.yzw(): Vector3d = Vector3d(y, z, w)

fun Vector4d.ywx(): Vector3d = Vector3d(y, w, x)

fun Vector4d.ywy(): Vector3d = Vector3d(y, w, y)

fun Vector4d.ywz(): Vector3d = Vector3d(y, w, z)

fun Vector4d.yww(): Vector3d = Vector3d(y, w, w)

fun Vector4d.zxx(): Vector3d = Vector3d(z, x, x)

fun Vector4d.zxy(): Vector3d = Vector3d(z, x, y)

fun Vector4d.zxz(): Vector3d = Vector3d(z, x, z)

fun Vector4d.zxw(): Vector3d = Vector3d(z, x, w)

fun Vector4d.zyx(): Vector3d = Vector3d(z, y, x)

fun Vector4d.zyy(): Vector3d = Vector3d(z, y, y)

fun Vector4d.zyz(): Vector3d = Vector3d(z, y, z)

fun Vector4d.zyw(): Vector3d = Vector3d(z, y, w)

fun Vector4d.zzx(): Vector3d = Vector3d(z, z, x)

fun Vector4d.zzy(): Vector3d = Vector3d(z, z, y)

fun Vector4d.zzz(): Vector3d = Vector3d(z, z, z)

fun Vector4d.zzw(): Vector3d = Vector3d(z, z, w)

fun Vector4d.zwx(): Vector3d = Vector3d(z, w, x)

fun Vector4d.zwy(): Vector3d = Vector3d(z, w, y)

fun Vector4d.zwz(): Vector3d = Vector3d(z, w, z)

fun Vector4d.zww(): Vector3d = Vector3d(z, w, w)

fun Vector4d.wxx(): Vector3d = Vector3d(w, x, x)

fun Vector4d.wxy(): Vector3d = Vector3d(w, x, y)

fun Vector4d.wxz(): Vector3d = Vector3d(w, x, z)

fun Vector4d.wxw(): Vector3d = Vector3d(w, x, w)

fun Vector4d.wyx(): Vector3d = Vector3d(w, y, x)

fun Vector4d.wyy(): Vector3d = Vector3d(w, y, y)

fun Vector4d.wyz(): Vector3d = Vector3d(w, y, z)

fun Vector4d.wyw(): Vector3d = Vector3d(w, y, w)

fun Vector4d.wzx(): Vector3d = Vector3d(w, z, x)

fun Vector4d.wzy(): Vector3d = Vector3d(w, z, y)

fun Vector4d.wzz(): Vector3d = Vector3d(w, z, z)

fun Vector4d.wzw(): Vector3d = Vector3d(w, z, w)

fun Vector4d.wwx(): Vector3d = Vector3d(w, w, x)

fun Vector4d.wwy(): Vector3d = Vector3d(w, w, y)

fun Vector4d.wwz(): Vector3d = Vector3d(w, w, z)

fun Vector4d.www(): Vector3d = Vector3d(w, w, w)

fun Vector4d.xxxx(): Vector4d = Vector4d(x, x, x, x)

fun Vector4d.xxxy(): Vector4d = Vector4d(x, x, x, y)

fun Vector4d.xxxz(): Vector4d = Vector4d(x, x, x, z)

fun Vector4d.xxxw(): Vector4d = Vector4d(x, x, x, w)

fun Vector4d.xxyx(): Vector4d = Vector4d(x, x, y, x)

fun Vector4d.xxyy(): Vector4d = Vector4d(x, x, y, y)

fun Vector4d.xxyz(): Vector4d = Vector4d(x, x, y, z)

fun Vector4d.xxyw(): Vector4d = Vector4d(x, x, y, w)

fun Vector4d.xxzx(): Vector4d = Vector4d(x, x, z, x)

fun Vector4d.xxzy(): Vector4d = Vector4d(x, x, z, y)

fun Vector4d.xxzz(): Vector4d = Vector4d(x, x, z, z)

fun Vector4d.xxzw(): Vector4d = Vector4d(x, x, z, w)

fun Vector4d.xxwx(): Vector4d = Vector4d(x, x, w, x)

fun Vector4d.xxwy(): Vector4d = Vector4d(x, x, w, y)

fun Vector4d.xxwz(): Vector4d = Vector4d(x, x, w, z)

fun Vector4d.xxww(): Vector4d = Vector4d(x, x, w, w)

fun Vector4d.xyxx(): Vector4d = Vector4d(x, y, x, x)

fun Vector4d.xyxy(): Vector4d = Vector4d(x, y, x, y)

fun Vector4d.xyxz(): Vector4d = Vector4d(x, y, x, z)

fun Vector4d.xyxw(): Vector4d = Vector4d(x, y, x, w)

fun Vector4d.xyyx(): Vector4d = Vector4d(x, y, y, x)

fun Vector4d.xyyy(): Vector4d = Vector4d(x, y, y, y)

fun Vector4d.xyyz(): Vector4d = Vector4d(x, y, y, z)

fun Vector4d.xyyw(): Vector4d = Vector4d(x, y, y, w)

fun Vector4d.xyzx(): Vector4d = Vector4d(x, y, z, x)

fun Vector4d.xyzy(): Vector4d = Vector4d(x, y, z, y)

fun Vector4d.xyzz(): Vector4d = Vector4d(x, y, z, z)

fun Vector4d.xyzw(): Vector4d = Vector4d(x, y, z, w)

fun Vector4d.xywx(): Vector4d = Vector4d(x, y, w, x)

fun Vector4d.xywy(): Vector4d = Vector4d(x, y, w, y)

fun Vector4d.xywz(): Vector4d = Vector4d(x, y, w, z)

fun Vector4d.xyww(): Vector4d = Vector4d(x, y, w, w)

fun Vector4d.xzxx(): Vector4d = Vector4d(x, z, x, x)

fun Vector4d.xzxy(): Vector4d = Vector4d(x, z, x, y)

fun Vector4d.xzxz(): Vector4d = Vector4d(x, z, x, z)

fun Vector4d.xzxw(): Vector4d = Vector4d(x, z, x, w)

fun Vector4d.xzyx(): Vector4d = Vector4d(x, z, y, x)

fun Vector4d.xzyy(): Vector4d = Vector4d(x, z, y, y)

fun Vector4d.xzyz(): Vector4d = Vector4d(x, z, y, z)

fun Vector4d.xzyw(): Vector4d = Vector4d(x, z, y, w)

fun Vector4d.xzzx(): Vector4d = Vector4d(x, z, z, x)

fun Vector4d.xzzy(): Vector4d = Vector4d(x, z, z, y)

fun Vector4d.xzzz(): Vector4d = Vector4d(x, z, z, z)

fun Vector4d.xzzw(): Vector4d = Vector4d(x, z, z, w)

fun Vector4d.xzwx(): Vector4d = Vector4d(x, z, w, x)

fun Vector4d.xzwy(): Vector4d = Vector4d(x, z, w, y)

fun Vector4d.xzwz(): Vector4d = Vector4d(x, z, w, z)

fun Vector4d.xzww(): Vector4d = Vector4d(x, z, w, w)

fun Vector4d.xwxx(): Vector4d = Vector4d(x, w, x, x)

fun Vector4d.xwxy(): Vector4d = Vector4d(x, w, x, y)

fun Vector4d.xwxz(): Vector4d = Vector4d(x, w, x, z)

fun Vector4d.xwxw(): Vector4d = Vector4d(x, w, x, w)

fun Vector4d.xwyx(): Vector4d = Vector4d(x, w, y, x)

fun Vector4d.xwyy(): Vector4d = Vector4d(x, w, y, y)

fun Vector4d.xwyz(): Vector4d = Vector4d(x, w, y, z)

fun Vector4d.xwyw(): Vector4d = Vector4d(x, w, y, w)

fun Vector4d.xwzx(): Vector4d = Vector4d(x, w, z, x)

fun Vector4d.xwzy(): Vector4d = Vector4d(x, w, z, y)

fun Vector4d.xwzz(): Vector4d = Vector4d(x, w, z, z)

fun Vector4d.xwzw(): Vector4d = Vector4d(x, w, z, w)

fun Vector4d.xwwx(): Vector4d = Vector4d(x, w, w, x)

fun Vector4d.xwwy(): Vector4d = Vector4d(x, w, w, y)

fun Vector4d.xwwz(): Vector4d = Vector4d(x, w, w, z)

fun Vector4d.xwww(): Vector4d = Vector4d(x, w, w, w)

fun Vector4d.yxxx(): Vector4d = Vector4d(y, x, x, x)

fun Vector4d.yxxy(): Vector4d = Vector4d(y, x, x, y)

fun Vector4d.yxxz(): Vector4d = Vector4d(y, x, x, z)

fun Vector4d.yxxw(): Vector4d = Vector4d(y, x, x, w)

fun Vector4d.yxyx(): Vector4d = Vector4d(y, x, y, x)

fun Vector4d.yxyy(): Vector4d = Vector4d(y, x, y, y)

fun Vector4d.yxyz(): Vector4d = Vector4d(y, x, y, z)

fun Vector4d.yxyw(): Vector4d = Vector4d(y, x, y, w)

fun Vector4d.yxzx(): Vector4d = Vector4d(y, x, z, x)

fun Vector4d.yxzy(): Vector4d = Vector4d(y, x, z, y)

fun Vector4d.yxzz(): Vector4d = Vector4d(y, x, z, z)

fun Vector4d.yxzw(): Vector4d = Vector4d(y, x, z, w)

fun Vector4d.yxwx(): Vector4d = Vector4d(y, x, w, x)

fun Vector4d.yxwy(): Vector4d = Vector4d(y, x, w, y)

fun Vector4d.yxwz(): Vector4d = Vector4d(y, x, w, z)

fun Vector4d.yxww(): Vector4d = Vector4d(y, x, w, w)

fun Vector4d.yyxx(): Vector4d = Vector4d(y, y, x, x)

fun Vector4d.yyxy(): Vector4d = Vector4d(y, y, x, y)

fun Vector4d.yyxz(): Vector4d = Vector4d(y, y, x, z)

fun Vector4d.yyxw(): Vector4d = Vector4d(y, y, x, w)

fun Vector4d.yyyx(): Vector4d = Vector4d(y, y, y, x)

fun Vector4d.yyyy(): Vector4d = Vector4d(y, y, y, y)

fun Vector4d.yyyz(): Vector4d = Vector4d(y, y, y, z)

fun Vector4d.yyyw(): Vector4d = Vector4d(y, y, y, w)

fun Vector4d.yyzx(): Vector4d = Vector4d(y, y, z, x)

fun Vector4d.yyzy(): Vector4d = Vector4d(y, y, z, y)

fun Vector4d.yyzz(): Vector4d = Vector4d(y, y, z, z)

fun Vector4d.yyzw(): Vector4d = Vector4d(y, y, z, w)

fun Vector4d.yywx(): Vector4d = Vector4d(y, y, w, x)

fun Vector4d.yywy(): Vector4d = Vector4d(y, y, w, y)

fun Vector4d.yywz(): Vector4d = Vector4d(y, y, w, z)

fun Vector4d.yyww(): Vector4d = Vector4d(y, y, w, w)

fun Vector4d.yzxx(): Vector4d = Vector4d(y, z, x, x)

fun Vector4d.yzxy(): Vector4d = Vector4d(y, z, x, y)

fun Vector4d.yzxz(): Vector4d = Vector4d(y, z, x, z)

fun Vector4d.yzxw(): Vector4d = Vector4d(y, z, x, w)

fun Vector4d.yzyx(): Vector4d = Vector4d(y, z, y, x)

fun Vector4d.yzyy(): Vector4d = Vector4d(y, z, y, y)

fun Vector4d.yzyz(): Vector4d = Vector4d(y, z, y, z)

fun Vector4d.yzyw(): Vector4d = Vector4d(y, z, y, w)

fun Vector4d.yzzx(): Vector4d = Vector4d(y, z, z, x)

fun Vector4d.yzzy(): Vector4d = Vector4d(y, z, z, y)

fun Vector4d.yzzz(): Vector4d = Vector4d(y, z, z, z)

fun Vector4d.yzzw(): Vector4d = Vector4d(y, z, z, w)

fun Vector4d.yzwx(): Vector4d = Vector4d(y, z, w, x)

fun Vector4d.yzwy(): Vector4d = Vector4d(y, z, w, y)

fun Vector4d.yzwz(): Vector4d = Vector4d(y, z, w, z)

fun Vector4d.yzww(): Vector4d = Vector4d(y, z, w, w)

fun Vector4d.ywxx(): Vector4d = Vector4d(y, w, x, x)

fun Vector4d.ywxy(): Vector4d = Vector4d(y, w, x, y)

fun Vector4d.ywxz(): Vector4d = Vector4d(y, w, x, z)

fun Vector4d.ywxw(): Vector4d = Vector4d(y, w, x, w)

fun Vector4d.ywyx(): Vector4d = Vector4d(y, w, y, x)

fun Vector4d.ywyy(): Vector4d = Vector4d(y, w, y, y)

fun Vector4d.ywyz(): Vector4d = Vector4d(y, w, y, z)

fun Vector4d.ywyw(): Vector4d = Vector4d(y, w, y, w)

fun Vector4d.ywzx(): Vector4d = Vector4d(y, w, z, x)

fun Vector4d.ywzy(): Vector4d = Vector4d(y, w, z, y)

fun Vector4d.ywzz(): Vector4d = Vector4d(y, w, z, z)

fun Vector4d.ywzw(): Vector4d = Vector4d(y, w, z, w)

fun Vector4d.ywwx(): Vector4d = Vector4d(y, w, w, x)

fun Vector4d.ywwy(): Vector4d = Vector4d(y, w, w, y)

fun Vector4d.ywwz(): Vector4d = Vector4d(y, w, w, z)

fun Vector4d.ywww(): Vector4d = Vector4d(y, w, w, w)

fun Vector4d.zxxx(): Vector4d = Vector4d(z, x, x, x)

fun Vector4d.zxxy(): Vector4d = Vector4d(z, x, x, y)

fun Vector4d.zxxz(): Vector4d = Vector4d(z, x, x, z)

fun Vector4d.zxxw(): Vector4d = Vector4d(z, x, x, w)

fun Vector4d.zxyx(): Vector4d = Vector4d(z, x, y, x)

fun Vector4d.zxyy(): Vector4d = Vector4d(z, x, y, y)

fun Vector4d.zxyz(): Vector4d = Vector4d(z, x, y, z)

fun Vector4d.zxyw(): Vector4d = Vector4d(z, x, y, w)

fun Vector4d.zxzx(): Vector4d = Vector4d(z, x, z, x)

fun Vector4d.zxzy(): Vector4d = Vector4d(z, x, z, y)

fun Vector4d.zxzz(): Vector4d = Vector4d(z, x, z, z)

fun Vector4d.zxzw(): Vector4d = Vector4d(z, x, z, w)

fun Vector4d.zxwx(): Vector4d = Vector4d(z, x, w, x)

fun Vector4d.zxwy(): Vector4d = Vector4d(z, x, w, y)

fun Vector4d.zxwz(): Vector4d = Vector4d(z, x, w, z)

fun Vector4d.zxww(): Vector4d = Vector4d(z, x, w, w)

fun Vector4d.zyxx(): Vector4d = Vector4d(z, y, x, x)

fun Vector4d.zyxy(): Vector4d = Vector4d(z, y, x, y)

fun Vector4d.zyxz(): Vector4d = Vector4d(z, y, x, z)

fun Vector4d.zyxw(): Vector4d = Vector4d(z, y, x, w)

fun Vector4d.zyyx(): Vector4d = Vector4d(z, y, y, x)

fun Vector4d.zyyy(): Vector4d = Vector4d(z, y, y, y)

fun Vector4d.zyyz(): Vector4d = Vector4d(z, y, y, z)

fun Vector4d.zyyw(): Vector4d = Vector4d(z, y, y, w)

fun Vector4d.zyzx(): Vector4d = Vector4d(z, y, z, x)

fun Vector4d.zyzy(): Vector4d = Vector4d(z, y, z, y)

fun Vector4d.zyzz(): Vector4d = Vector4d(z, y, z, z)

fun Vector4d.zyzw(): Vector4d = Vector4d(z, y, z, w)

fun Vector4d.zywx(): Vector4d = Vector4d(z, y, w, x)

fun Vector4d.zywy(): Vector4d = Vector4d(z, y, w, y)

fun Vector4d.zywz(): Vector4d = Vector4d(z, y, w, z)

fun Vector4d.zyww(): Vector4d = Vector4d(z, y, w, w)

fun Vector4d.zzxx(): Vector4d = Vector4d(z, z, x, x)

fun Vector4d.zzxy(): Vector4d = Vector4d(z, z, x, y)

fun Vector4d.zzxz(): Vector4d = Vector4d(z, z, x, z)

fun Vector4d.zzxw(): Vector4d = Vector4d(z, z, x, w)

fun Vector4d.zzyx(): Vector4d = Vector4d(z, z, y, x)

fun Vector4d.zzyy(): Vector4d = Vector4d(z, z, y, y)

fun Vector4d.zzyz(): Vector4d = Vector4d(z, z, y, z)

fun Vector4d.zzyw(): Vector4d = Vector4d(z, z, y, w)

fun Vector4d.zzzx(): Vector4d = Vector4d(z, z, z, x)

fun Vector4d.zzzy(): Vector4d = Vector4d(z, z, z, y)

fun Vector4d.zzzz(): Vector4d = Vector4d(z, z, z, z)

fun Vector4d.zzzw(): Vector4d = Vector4d(z, z, z, w)

fun Vector4d.zzwx(): Vector4d = Vector4d(z, z, w, x)

fun Vector4d.zzwy(): Vector4d = Vector4d(z, z, w, y)

fun Vector4d.zzwz(): Vector4d = Vector4d(z, z, w, z)

fun Vector4d.zzww(): Vector4d = Vector4d(z, z, w, w)

fun Vector4d.zwxx(): Vector4d = Vector4d(z, w, x, x)

fun Vector4d.zwxy(): Vector4d = Vector4d(z, w, x, y)

fun Vector4d.zwxz(): Vector4d = Vector4d(z, w, x, z)

fun Vector4d.zwxw(): Vector4d = Vector4d(z, w, x, w)

fun Vector4d.zwyx(): Vector4d = Vector4d(z, w, y, x)

fun Vector4d.zwyy(): Vector4d = Vector4d(z, w, y, y)

fun Vector4d.zwyz(): Vector4d = Vector4d(z, w, y, z)

fun Vector4d.zwyw(): Vector4d = Vector4d(z, w, y, w)

fun Vector4d.zwzx(): Vector4d = Vector4d(z, w, z, x)

fun Vector4d.zwzy(): Vector4d = Vector4d(z, w, z, y)

fun Vector4d.zwzz(): Vector4d = Vector4d(z, w, z, z)

fun Vector4d.zwzw(): Vector4d = Vector4d(z, w, z, w)

fun Vector4d.zwwx(): Vector4d = Vector4d(z, w, w, x)

fun Vector4d.zwwy(): Vector4d = Vector4d(z, w, w, y)

fun Vector4d.zwwz(): Vector4d = Vector4d(z, w, w, z)

fun Vector4d.zwww(): Vector4d = Vector4d(z, w, w, w)

fun Vector4d.wxxx(): Vector4d = Vector4d(w, x, x, x)

fun Vector4d.wxxy(): Vector4d = Vector4d(w, x, x, y)

fun Vector4d.wxxz(): Vector4d = Vector4d(w, x, x, z)

fun Vector4d.wxxw(): Vector4d = Vector4d(w, x, x, w)

fun Vector4d.wxyx(): Vector4d = Vector4d(w, x, y, x)

fun Vector4d.wxyy(): Vector4d = Vector4d(w, x, y, y)

fun Vector4d.wxyz(): Vector4d = Vector4d(w, x, y, z)

fun Vector4d.wxyw(): Vector4d = Vector4d(w, x, y, w)

fun Vector4d.wxzx(): Vector4d = Vector4d(w, x, z, x)

fun Vector4d.wxzy(): Vector4d = Vector4d(w, x, z, y)

fun Vector4d.wxzz(): Vector4d = Vector4d(w, x, z, z)

fun Vector4d.wxzw(): Vector4d = Vector4d(w, x, z, w)

fun Vector4d.wxwx(): Vector4d = Vector4d(w, x, w, x)

fun Vector4d.wxwy(): Vector4d = Vector4d(w, x, w, y)

fun Vector4d.wxwz(): Vector4d = Vector4d(w, x, w, z)

fun Vector4d.wxww(): Vector4d = Vector4d(w, x, w, w)

fun Vector4d.wyxx(): Vector4d = Vector4d(w, y, x, x)

fun Vector4d.wyxy(): Vector4d = Vector4d(w, y, x, y)

fun Vector4d.wyxz(): Vector4d = Vector4d(w, y, x, z)

fun Vector4d.wyxw(): Vector4d = Vector4d(w, y, x, w)

fun Vector4d.wyyx(): Vector4d = Vector4d(w, y, y, x)

fun Vector4d.wyyy(): Vector4d = Vector4d(w, y, y, y)

fun Vector4d.wyyz(): Vector4d = Vector4d(w, y, y, z)

fun Vector4d.wyyw(): Vector4d = Vector4d(w, y, y, w)

fun Vector4d.wyzx(): Vector4d = Vector4d(w, y, z, x)

fun Vector4d.wyzy(): Vector4d = Vector4d(w, y, z, y)

fun Vector4d.wyzz(): Vector4d = Vector4d(w, y, z, z)

fun Vector4d.wyzw(): Vector4d = Vector4d(w, y, z, w)

fun Vector4d.wywx(): Vector4d = Vector4d(w, y, w, x)

fun Vector4d.wywy(): Vector4d = Vector4d(w, y, w, y)

fun Vector4d.wywz(): Vector4d = Vector4d(w, y, w, z)

fun Vector4d.wyww(): Vector4d = Vector4d(w, y, w, w)

fun Vector4d.wzxx(): Vector4d = Vector4d(w, z, x, x)

fun Vector4d.wzxy(): Vector4d = Vector4d(w, z, x, y)

fun Vector4d.wzxz(): Vector4d = Vector4d(w, z, x, z)

fun Vector4d.wzxw(): Vector4d = Vector4d(w, z, x, w)

fun Vector4d.wzyx(): Vector4d = Vector4d(w, z, y, x)

fun Vector4d.wzyy(): Vector4d = Vector4d(w, z, y, y)

fun Vector4d.wzyz(): Vector4d = Vector4d(w, z, y, z)

fun Vector4d.wzyw(): Vector4d = Vector4d(w, z, y, w)

fun Vector4d.wzzx(): Vector4d = Vector4d(w, z, z, x)

fun Vector4d.wzzy(): Vector4d = Vector4d(w, z, z, y)

fun Vector4d.wzzz(): Vector4d = Vector4d(w, z, z, z)

fun Vector4d.wzzw(): Vector4d = Vector4d(w, z, z, w)

fun Vector4d.wzwx(): Vector4d = Vector4d(w, z, w, x)

fun Vector4d.wzwy(): Vector4d = Vector4d(w, z, w, y)

fun Vector4d.wzwz(): Vector4d = Vector4d(w, z, w, z)

fun Vector4d.wzww(): Vector4d = Vector4d(w, z, w, w)

fun Vector4d.wwxx(): Vector4d = Vector4d(w, w, x, x)

fun Vector4d.wwxy(): Vector4d = Vector4d(w, w, x, y)

fun Vector4d.wwxz(): Vector4d = Vector4d(w, w, x, z)

fun Vector4d.wwxw(): Vector4d = Vector4d(w, w, x, w)

fun Vector4d.wwyx(): Vector4d = Vector4d(w, w, y, x)

fun Vector4d.wwyy(): Vector4d = Vector4d(w, w, y, y)

fun Vector4d.wwyz(): Vector4d = Vector4d(w, w, y, z)

fun Vector4d.wwyw(): Vector4d = Vector4d(w, w, y, w)

fun Vector4d.wwzx(): Vector4d = Vector4d(w, w, z, x)

fun Vector4d.wwzy(): Vector4d = Vector4d(w, w, z, y)

fun Vector4d.wwzz(): Vector4d = Vector4d(w, w, z, z)

fun Vector4d.wwzw(): Vector4d = Vector4d(w, w, z, w)

fun Vector4d.wwwx(): Vector4d = Vector4d(w, w, w, x)

fun Vector4d.wwwy(): Vector4d = Vector4d(w, w, w, y)

fun Vector4d.wwwz(): Vector4d = Vector4d(w, w, w, z)

fun Vector4d.wwww(): Vector4d = Vector4d(w, w, w, w)
