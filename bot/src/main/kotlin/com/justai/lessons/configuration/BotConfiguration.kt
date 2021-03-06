package com.justai.lessons.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "bot")
data class BotConfiguration(
    val token: String,
    val mongoCollection: String
)