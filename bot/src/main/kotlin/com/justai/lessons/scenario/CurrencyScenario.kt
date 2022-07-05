package com.justai.lessons.scenario

import com.justai.jaicf.builder.Scenario
import com.justai.lessons.extension.CurrencyService
import com.justai.lessons.extension.getCustomCurrency

val currencyScenario = Scenario {
    state("Currency"){
        activators{
            intent("Currency")
        }
        action {
            getCustomCurrency()?.let {
                reactions.say("Курс ${it.acc} составляет ${CurrencyService.getRate(it.code)} р.")
            }
        }
    }
}