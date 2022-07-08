package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario

val bargeInContextScenario = Scenario {
    state("BargeInContext", modal = true) {
        state("WillPay") {
            activators {
                intent("StrongAgree")
            }

            action {
                reactions.go("/Start/WillPay")
            }
        }
    }
}
