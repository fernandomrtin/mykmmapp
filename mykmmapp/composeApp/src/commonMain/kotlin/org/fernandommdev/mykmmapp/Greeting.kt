package org.fernandommdev.mykmmapp

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello test, ${platform.name}!"
    }
}