package net.wiredtomato.perception.systems.cache

import net.wiredtomato.perception.systems.util.KRunnable
import net.wiredtomato.perception.systems.util.KTriFunc
import java.util.concurrent.ConcurrentHashMap

private val onInvalidateCaches = mutableListOf<Pair<CacheType, KRunnable>>()


fun <P, P2, P3, R> memoize(cacheType: CacheType, func: KTriFunc<P, P2, P3, R>): KTriFunc<P,  P2, P3, R> {
    val memoized = object : KTriFunc<P, P2, P3, R> {
        var cache: ConcurrentHashMap<Triple<P, P2, P3>, R> = ConcurrentHashMap()

        override fun invoke(p1: P, p2: P2, p3: P3): R {
            return cache.computeIfAbsent(Triple(p1, p2, p3)) { triple ->
                func(triple.first, triple.second, triple.third)
            }
        }
    }

    onInvalidateCaches.add(cacheType to {
        memoized.cache = ConcurrentHashMap()
    })

    return memoized
}

fun addCacheInvalidationTarget(cacheType: CacheType, invalidate: KRunnable) {
    onInvalidateCaches += cacheType to invalidate
}

fun invalidateCaches(cacheType: CacheType) {
    onInvalidateCaches.forEach { (cType, invalidate) ->
        if (cacheType == cType) invalidate()
    }
}

data class CacheType(val id: Int)
val MODEL_CACHE = CacheType(0)
