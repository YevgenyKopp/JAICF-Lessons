package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.lessons.extension.CurrencyService

val mainScenario = Scenario {
    state("Start") {
        activators {
            regex("/start")
        }

        action {
            reactions.say("Привет, купи слона")
        }

        state("CurrencyRequest"){
            activators {
                regex("Курс доллара")
            }

            action {
                reactions.say("Курс доллара: ${CurrencyService.getRate()} р.")
            }
        }
    }
}