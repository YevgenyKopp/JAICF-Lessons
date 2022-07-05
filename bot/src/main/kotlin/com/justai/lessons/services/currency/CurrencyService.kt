package com.justai.lessons.services.currency

import com.google.gson.Gson
import com.justai.lessons.services.currency.model.CurrencyRates
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class CurrencyService {
    private val client = createCurrencyHttpClient()

    fun getRate(currency: String = "USD") = runBlocking(Dispatchers.IO) {
        Gson().fromJson(client.get<String>(), CurrencyRates::class.java)
    }.rates[currency]?.let { 1 / it }
}