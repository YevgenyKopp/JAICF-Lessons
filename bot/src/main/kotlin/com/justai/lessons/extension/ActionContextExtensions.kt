package com.justai.lessons.extension

import com.google.gson.Gson
import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.jaicf.context.DefaultActionContext
import com.justai.lessons.extension.model.CityData
import com.justai.lessons.extension.model.CustomCurrency
import com.justai.lessons.extension.model.TimeData

fun DefaultActionContext.getCustomCurrency() =
    activator.caila?.entities?.first { it.entity == "Currency" }?.value?.let {
        Gson().fromJson(it, CustomCurrency::class.java)
    }

fun DefaultActionContext.getCity(): CityData =
    Gson().fromJson(activator.caila?.slots?.get("City"), CityData::class.java)

fun DefaultActionContext.getTime(): TimeData =
    Gson().fromJson(activator.caila?.slots?.get("Time"), TimeData::class.java)

fun DefaultActionContext.sayFallbackOrTransfer(fallbackText: String, transferText: String) {
    val counter = if (context.previousState == context.dialogContext.currentState) context.stateCounter else 0

    if (counter < 2)
        reactions.say(fallbackText)
    else {
        reactions.say(transferText)
        reactions.telephony?.transferCall("34593483")
    }

    context.stateCounter = counter + 1
}