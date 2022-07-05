package com.justai.lessons.scenario

import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.activator.caila.cailaEntity
import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.lessons.extension.ElephantService
import com.justai.lessons.serializers.CityData
import com.justai.lessons.serializers.Kotlinx
import com.justai.lessons.serializers.TimeData

val startScenario = Scenario {
    state(MyStates.START) {
        activators {
            regex("/start")
        }
        action {
            reactions.say("Привет, купи слона")
        }

        state(MyStates.YES) {
            activators {
                regex("давай")
            }
            action {
                reactions.say("Какой именно слон вас интересует?")
            }

            state("Elephant Type") {
                activators {
                    regex("большой")
                }

                action {
                    if (ElephantService.isAvailable(request.input)) {
                        reactions.say("Когда вы сможете принять доставку?")
                    } else {
                        reactions.say("Таких слонов у нас нет, назовите других")
                        reactions.changeState("..")
                    }
                }

                //Стейт на встроенную сущность
                state(MyStates.TIME) {
                    activators {
                        cailaEntity("duckling.time")
                    }

                    action {
                        reactions.say("И последнее, в какой город нужно доставить?")
                    }

                    //Стейт на встроенную/кастомную сущность
                    state(MyStates.CITY) {
                        activators {
                            cailaEntity("City")
                        }

                        action {
                            reactions.say("Всё зафиксировано, ожидайте доставки")
                            reactions.telephony?.hangup()
                        }
                    }

                    fallback {
                        reactions.say("Ничего не понял, назовите ваш город")
                    }
                }
                fallback {
                    reactions.say("Мм... Не совсем понял, когда посылочку примите?")
                }
            }

            fallback {
                reactions.say("Я таких слонов не знаю")
            }
        }

        state(MyStates.ORDER) {
            globalActivators {
                intent("OrderAnAnimal")
            }

            action {
                // сущность из слота
                val cityEntity = activator.caila?.slots?.get("City")
                val dateEntity = activator.caila?.slots?.get("Time")

                // сериализация
                val deliveryCity = cityEntity?.let { Kotlinx.decodeFromString(CityData.serializer(), it) }
                val deliveryTime = dateEntity?.let { Kotlinx.decodeFromString(TimeData.serializer(), it) }

                reactions.say("Всё зафиксировано, ожидайте доставки $deliveryTime " +
                        "в город $deliveryCity :)")
                reactions.telephony?.hangup()
            }
        }

        fallback {
            reactions.say("Все говорят ${request.input}, а ты купи слона")
        }
    }
}

object MyStates {
    const val START = "Start"
    const val YES = "Yes"
    const val TIME = "DeliveryTime"
    const val CITY = "DeliveryCity"
    const val ORDER = "OrderAnAnimal"
}