package com.justai.lessons.connection

import com.justai.jaicf.api.BotApi
import com.justai.jaicf.channel.ConsoleChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import kotlin.coroutines.CoroutineContext

@Component
class Console(
    private val botApi: BotApi
): ApplicationRunner, CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.Default

    override fun run(args: ApplicationArguments?) {
        launch {
            ConsoleChannel(botApi).run()
        }
    }
}
