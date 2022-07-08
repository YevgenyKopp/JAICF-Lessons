package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.jaicf.hook.AfterActionHook
import com.justai.jaicf.hook.AfterProcessHook
import com.justai.jaicf.hook.AnyErrorHook
import com.justai.lessons.extension.SomeConfiguration
import com.justai.lessons.extension.sayDefault
import com.justai.lessons.extension.sayFallbackOrBye
import com.justai.lessons.services.phrases.Phrases

val configScenario = Scenario {

    append(bargeInContextScenario)

    state("Start") {
        activators {
            regex("/start")
        }
        action {
            reactions.telephony?.startNewSession() ?: context.cleanSessionData()

            reactions.say("${SomeConfiguration.enabled}: ${SomeConfiguration.text}")

            reactions.telephony?.say("Здесь очень длинная фраза", bargeInContext = "/BargeInContext")
        }

        state("WillPay") {
            activators {
                intent("StrongAgree")
                intent("Yes")
            }
        }
    }

    state("Buy") {
        activators {
            regex("buy")
        }

        action {
            sayDefault(Phrases.buy)
        }

        fallback {
            sayFallbackOrBye(Phrases.buy)
        }
    }

    state("Delivery") {
        activators {
            regex("delivery")
        }

        action {
            sayDefault(Phrases.delivery)
        }

        fallback {
            sayFallbackOrBye(Phrases.delivery)
        }
    }

    state("Operator") {
        activators {
            regex("operator")
        }

        action {
            sayDefault(Phrases.operator)
            reactions.telephony?.transferCall("number")
        }
    }
}