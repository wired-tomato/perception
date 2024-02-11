package net.wiredtomato.perception.client.config

import eu.midnightdust.lib.config.MidnightConfig

object ClientConfig: MidnightConfig() {
    @Entry
    val DELAYED_RENDERING = true
}