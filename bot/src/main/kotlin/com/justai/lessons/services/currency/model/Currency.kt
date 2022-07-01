package com.justai.lessons.services.currency.model

data class Currency(
    val disclaimer: String,
    val date: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)