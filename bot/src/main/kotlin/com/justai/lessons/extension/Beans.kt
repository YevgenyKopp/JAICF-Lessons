package com.justai.lessons.extension

import com.justai.lessons.services.ElephantService
import com.justai.lessons.services.bean
import com.justai.lessons.services.currency.CurrencyService

val ElephantService by bean<ElephantService>()
val CurrencyService by bean<CurrencyService>()