package com.justai.lessons.serializers

import kotlinx.serialization.Serializable

@Serializable
data class CityData(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}

@Serializable
data class TimeData(
    val year: String,
    val month: String,
    val day: String
) {
    override fun toString(): String {
        return "$day/$month/$year"
    }
}