package com.justai.lessons.extension

import com.google.gson.Gson
import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.channel.jaicp.reactions.telephony
import com.justai.jaicf.context.DefaultActionContext
import com.justai.lessons.extension.model.CityData
import com.justai.lessons.extension.model.CustomCurrency
import com.justai.lessons.extension.model.TimeData
import com.justai.lessons.services.phrases.Phrase
import com.justai.lessons.services.phrases.Phrases

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

fun DefaultActionContext.sayDefault(phrase: Phrase) {
    PhraseContentProvider.getByPhrase(phrase)?.default?.let { defaultPhrases ->
        val counter = context.session.getOrPut("DefaultPhrase${phrase.name}") { 0 } as Int

        reactions.say(defaultPhrases[counter % defaultPhrases.size])

        context.session["DefaultPhrase${phrase.name}"] = counter + 1
    } ?: error("Cannot get default texts for phrase: ${phrase.name}")
}

fun DefaultActionContext.sayFallbackOrBye(phrase: Phrase) {
    PhraseContentProvider.getByPhrase(phrase)?.fallback?.let { fallbackPhrases ->
        val counter = context.session.getOrPut("FallbackPhrase${phrase.name}") { 0 } as Int

        if (counter < fallbackPhrases.size)
            reactions.say(fallbackPhrases[counter])
        else
            sayByeAndHangup()

        context.session["FallbackPhrase${phrase.name}"] = counter + 1
    } ?: error("Cannot get fallback texts for phrase: ${phrase.name}")
}

fun DefaultActionContext.sayByeAndHangup() {
    PhraseContentProvider.getByPhrase(Phrases.bye)?.default?.get(0)?.let {
        reactions.say(it)
        reactions.telephony?.hangup()
    } ?: error("Cannot get bye phrase")
}