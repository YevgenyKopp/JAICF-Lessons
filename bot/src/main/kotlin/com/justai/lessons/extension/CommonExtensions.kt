package com.justai.lessons.extension

fun String.getFirstUOrA() = this.first { it == "U".single() || it == "A".single() }


fun main() {
    println("JNFJHUSDADF".getFirstUOrA())
}