package com.justai.lessons.extension

import com.google.gson.Gson
import com.justai.jaicf.activator.caila.caila
import com.justai.jaicf.context.DefaultActionContext
import com.justai.lessons.extension.model.CityData
import com.justai.lessons.extension.model.CustomCurrency
import com.justai.lessons.extension.model.TimeData

fun DefaultActionContext.getCustomCurrency() =
    activator.caila?.entities?.first { it.entity == "Currency" }?.value?.let {
        Gson().fromJson(it, CustomCurrency::class.java)
    }

fun DefaultActionContext.getCity() =
    Gson().fromJson(activator.caila?.slots?.get("City"), CityData::class.java)

fun DefaultActionContext.getTime() =
    Gson().fromJson(activator.caila?.slots?.get("Time"), TimeData::class.java)