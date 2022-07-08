package bot

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.caila.CailaIntentActivator
import com.justai.jaicf.activator.caila.CailaNLUSettings
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.test.BotTest
import com.justai.lessons.scenario.testScenario

open class MyBotTest : BotTest(
    BotEngine(
        scenario = testScenario,
        activators = arrayOf(
            RegexActivator,
            CailaIntentActivator.Factory(
                CailaNLUSettings(
                    accessToken = "dd38bcfd-4604-4f56-8603-b1618cead7af"
                )
            )
        )
    )
)