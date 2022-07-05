package com.justai.lessons.extension

import com.google.gson.Gson
import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.context.DefaultActionContext
import com.justai.lessons.extension.model.CustomCurrency

fun DefaultActionContext.getCustomCurrency() =
    activator.caila?.entities?.first { it.entity == "Currency" }?.value?.let {
        Gson().fromJson(it, CustomCurrency::class.java)
    }