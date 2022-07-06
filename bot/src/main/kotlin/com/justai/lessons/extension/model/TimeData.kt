package com.justai.lessons.extension.model

data class TimeData(
    val year: String,
    val month: String,
    val day: String
) {
    override fun toString(): String {
        return "$day/$month/$year"
    }
}