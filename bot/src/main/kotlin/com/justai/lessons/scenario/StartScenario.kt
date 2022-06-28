package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.telephony
import com.justai.lessons.extension.ElephantService

val startScenario = Scenario(telephony) {
    state(MyStates.START) {
        activators {
            regex("/start")
        }
        action {
            reactions.say("Привет, купи слона")
        }

        state(MyStates.BUY_ELEPHANT) {
            activators {
                regex("давай")
            }
            action {
                reactions.say("Какой именно слон вас интересует")
            }

            //Стейт на кастомную сущность
            state("Elephant Type") {
                activators { }

                action {
                    if (ElephantService.isAvailable(request.input)) {
                        reactions.say("А сколько слонов вам нужно")
                    } else {
                        reactions.say("Таких слонов у нас нет, назовите других")
                        reactions.changeState("..")
                    }
                }

                //Стейт на встроенную сущность
                state("Elephant Number") {
                    activators { }

                    action {
                        reactions.say("И последнее, в какой город нужно доставить?")
                    }

                    //Стейт на встроенную/кастомную сущность
                    state("City") {
                        activators { }

                        action {
                            reactions.say("Всё зафиксировано, ожидайте доставки")
                            reactions.hangup()
                        }
                    }

                    fallback {
                        reactions.say("Ничего не понял, назовите ваш город")
                    }
                }
                fallback {
                    reactions.say("Мм... Не совсем понял, назовите число")
                }
            }

            fallback {
                reactions.say("Я таких слонов не знаю")
            }
        }

        fallback {
            reactions.say("Все говорят ${request.input}, а ты купи слона")
        }
    }
}

object MyStates {
    const val START = "Start"
    const val BUY_ELEPHANT = "Buy"
}