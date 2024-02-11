package net.wiredtomato.perception.systems.util

typealias KSupplier<T> = () -> T
fun <T> KSupplier<T>.get() = this()

typealias KConsumer<T> = (T) -> Unit
fun <T> KConsumer<T>.accept(value: T) = this(value)

typealias KFunc<P, R> = (P) -> R
fun <P, R> KFunc<P, R>.apply(param: P) = this(param)

typealias KBiFunc<P, P2, R> = (P, P2) -> R
fun <P, P2, R> KBiFunc<P, P2, R>.apply(p: P, p2: P2) = this(p, p2)

typealias KTriFunc<P, P2, P3, R> = (P, P2, P3) -> R
fun <P, P2, P3, R> KTriFunc<P, P2, P3, R>.apply(p: P, p2: P2, p3: P3) = this(p, p2, p3)

typealias KRunnable = () -> Unit
fun KRunnable.run() = this()
