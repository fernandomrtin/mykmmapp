package org.fernandommdev.mykmmapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform