package com.flosy.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
