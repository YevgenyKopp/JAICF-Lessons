package com.justai.lessons.services.currency.model

data class CurrencyRates(
    val disclaimer: String,
    val date: String,
    val timestamp: Long,
    val base: String,
    val rates: Map<String, Double>
)