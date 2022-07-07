package com.justai.lessons.connection

import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpPollingConnector
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.lessons.configuration.BotConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class JaicpPoller(
    private val botApi: BotApi,
    private val botConfiguration: BotConfiguration
): ApplicationRunner, CoroutineScope {
    override val coroutineContext: CoroutineContext =
        Dispatchers.Default

    override fun run(args: ApplicationArguments?) {
        launch {
            JaicpPollingConnector(
                botApi = botApi,
                accessToken = botConfiguration.token,
                channels = listOf(
                    TelephonyChannel,
                    TelegramChannel
                )
            ).runBlocking()
        }
    }
}