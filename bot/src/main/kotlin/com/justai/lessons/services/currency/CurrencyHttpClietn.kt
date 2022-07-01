package com.justai.lessons.services.currency

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import org.slf4j.LoggerFactory

fun createCurrencyHttpClient() = HttpClient(Apache) {
    expectSuccess = false
    install(Logging) {
        level = LogLevel.ALL
        logger = HttpLogger("CurrencyHttpClient")
    }
    engine {
        connectTimeout = 2_500
        socketTimeout = 10_000
        connectionRequestTimeout = 10_000
    }
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "www.cbr-xml-daily.ru"
            path("latest.js")
        }
    }
}

class HttpLogger(name: String) : Logger {
    private val logger = LoggerFactory.getLogger(name)

    override fun log(message: String) {
        logger.info(message)
    }
}