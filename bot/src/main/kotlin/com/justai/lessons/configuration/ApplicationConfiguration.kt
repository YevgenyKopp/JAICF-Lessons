package com.justai.lessons.configuration

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.jaicp.JaicpWebhookConnector
import com.justai.jaicf.channel.jaicp.logging.JaicpConversationLogger
import com.justai.jaicf.channel.telegram.TelegramChannel
import com.justai.jaicf.context.manager.mongo.MongoBotContextManager
import com.justai.jaicf.logging.Slf4jConversationLogger
import com.justai.lessons.scenario.configScenario
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory

@Configuration
class ApplicationConfiguration(
    private val botConfiguration: BotConfiguration,
    private val mongoDatabaseFactory: MongoDatabaseFactory
) {
    @Bean
    fun botApi() =
        BotEngine(
            scenario = configScenario,
            defaultContextManager = mongoDatabaseFactory.createContextManager(botConfiguration.mongoCollection),
            activators = arrayOf(
                RegexActivator,
                createCailaActivator(botConfiguration.token),
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
            channels = listOf(TelegramChannel)
        )

    companion object {
        private fun createCailaActivator(token: String) =
            CailaIntentActivator.Factory(CailaNLUSettings(token))

        private fun MongoDatabaseFactory.createContextManager(collection: String) =
                MongoBotContextManager(mongoDatabase.getCollection(collection))
    }
}