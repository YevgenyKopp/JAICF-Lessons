package com.justai.lessons.configuration

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import com.justai.jaicf.channel.jaicp.channels.TelephonyChannel
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.lessons.scenario.startScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfiguration(
    private val botConfiguration: BotConfiguration
) {
    @Bean
    fun botApi() =
        BotEngine(
            scenario = startScenario,
            activators = arrayOf(
                createCailaActivator(botConfiguration.token),
                RegexActivator,
                CatchAllActivator
            ),
            conversationLoggers = arrayOf(
                JaicpConversationLogger(botConfiguration.token),
                Slf4jConversationLogger()
            )
        )

    @Bean
    fun jaicpWebhookConnector(botApi: BotApi) =
        JaicpWebhookConnector(
            botApi = botApi,
            accessToken = botConfiguration.token,
            channels = listOf(TelephonyChannel)
        )

    companion object {
        private fun createCailaActivator(token: String) =
            CailaIntentActivator.Factory(CailaNLUSettings(token))
    }
}