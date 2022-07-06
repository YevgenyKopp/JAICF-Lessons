package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.lessons.extension.ElephantService

val mainScenario = Scenario {

    append(currencyScenario)

    state("Start") {
        activators {
            regex("/start")
        }
        action {
            reactions.say("Привет, купи слона")
        }

        state("Buy") {
            activators {
                regex("Давай")
            }
            action {
                reactions.say("А какой слон вас интересует")
            }

            state("ElephantType") {
                activators {
                    catchAll()
                }
                action {
                    if (ElephantService.isAvailable(request.input)) {
                        reactions.say("Окей, привезём")
                        reactions.go("../../../GoodBye")
                    } else {
                        reactions.say("Ой, а таких у нас нет, давай другого?")
                        reactions.changeState("..")
                    }
                }
            }
        }

        fallback {
            reactions.say("Все говорят ${request.input}, а ты купи слона.")
        }
    }

    state("GoodBye") {
        action {
            reactions.say("Ну, пока")
            reactions.telephony?.hangup()
        }
    }
}