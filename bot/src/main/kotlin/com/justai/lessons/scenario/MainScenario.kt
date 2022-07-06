package com.justai.lessons.scenario

import com.justai.jaicf.activator.caila.cailaEntity
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.lessons.extension.ElephantService
import com.justai.lessons.extension.getCity
import com.justai.lessons.extension.getTime

val mainScenario = Scenario {

    append(currencyScenario)

    state("Start") {
        globalActivators {
            regex("/start")
            intent("Hello")
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
                    cailaEntity("mystem.persn")
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

        state("Order") {
            globalActivators {
                intent("Order")
            }

            action {
//                println(activator.caila?.slots?.get("City"))
                val deliveryCity = getCity().name
                val deliveryTime = getTime()
                reactions.say("Окей, привезём в город $deliveryCity $deliveryTime!")
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