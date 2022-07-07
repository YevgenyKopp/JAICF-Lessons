package com.justai.lessons.extension

import com.justai.jaicf.context.BotContext

val BotContext.previousState: String
    get() = dialogContext.transitionHistory.toList().let { it[it.size - 2] }

var BotContext.stateCounter
    get() = session.getOrPut("Counter${dialogContext.currentState}") { 0 } as Int
    set(value) {
        session["Counter${dialogContext.currentState}"] = value
    }