package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario

val testScenario = Scenario {
    state("Start") {
        activators {
            regex("/start")
        }
        action {
            reactions.say("hello!")
            reactions.go("../Order")
        }
    }

    state("Order") {
        action {
            reactions.say("Would you like to order?")
        }

        state("Agree") {
            activators {
                intent("yes")
            }
            action {
                reactions.say("Thank you")
            }
        }

        state("Disagree") {
            activators {
                intent("no")
            }
            action {
                reactions.say("Okay :(")
            }
        }

        state("Check") {
            activators {
                intent("check")
            }
            action {
                if (context.session["flag"] as? Boolean == true)
                    reactions.go("byTrue")
                else
                    reactions.go("byFalse")

            }

            state("byTrue") {

            }

            state("byFalse") {

            }
        }
    }
}