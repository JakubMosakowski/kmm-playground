package com.jakmos.counter

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}