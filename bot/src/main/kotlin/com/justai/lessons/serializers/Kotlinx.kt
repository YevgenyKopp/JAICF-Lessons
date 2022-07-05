package com.justai.lessons.serializers
import kotlinx.serialization.json.Json

val Kotlinx = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    isLenient = true
}