package com.justai.lessons.scenario

import com.justai.jaicf.activator.caila.cailaEntity
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.lessons.extension.getCity
import com.justai.lessons.extension.getTime
import com.justai.lessons.extension.sayFallbackOrTransfer

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
                    cailaEntity("elaphantType")
                }
                action {
                    context.session["elephantType"] = activator.cailaEntity?.value
                    reactions.say("А в какой город везти?")
                }
                state("City") {
                    activators {
                        cailaEntity("city")
                    }
                    action {
                        activator.cailaEntity?.value?.let {
                            reactions.say("Отлично! привезём ${context.session["elephantType"]} в $it")
                        }

                        reactions.go("../../../../GoodBye")
                    }
                }
                fallback {
                    sayFallbackOrTransfer(
                        fallbackText = "Простите, ещё раз, в какой город привезти?",
                        transferText = "Для определения города свяжу вас с нашим специалистом"
                    )
                }
            }

            fallback {
                sayFallbackOrTransfer(
                    fallbackText = "Простите, ещё раз, какой слон вас интересует?",
                    transferText = "Для определения типа слона свяжу вас с нашим специалистом"
                )
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