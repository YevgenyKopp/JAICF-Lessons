package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.lessons.extension.sayFallbackOrTransfer

val contextScenario = Scenario {
    state("Start"){
        activators {
            regex("/start")
        }
        action {
            reactions.telephony?.startNewSession() ?: context.cleanSessionData()
            reactions.say("New session started")
        }
    }

    state("SetContext"){
        activators {
            regex("Set")
        }
        action {
            context.session["someSignificantKey"] = "session value"
            context.client["someSignificantKey"] = "client value"

            reactions.say("done!")
        }
    }

    state("GetContext"){
        activators {
            regex("get")
        }
        action {
            reactions.say("Your session value is: ${context.session["someSignificantKey"]}")
            reactions.say("Your client value is: ${context.client["someSignificantKey"]}")
        }
    }

    fallback {
        sayFallbackOrTransfer()
    }
}