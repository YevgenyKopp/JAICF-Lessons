package com.justai.lessons.extension

import com.justai.lessons.configuration.SomeConfiguration
import com.justai.lessons.services.ElephantService
import com.justai.lessons.services.bean
import com.justai.lessons.services.currency.CurrencyService
import com.justai.lessons.services.phrases.PhraseContentProvider

val ElephantService by bean<ElephantService>()

val CurrencyService by bean<CurrencyService>()

val PhraseContentProvider by bean<PhraseContentProvider>()

val SomeConfiguration by bean<SomeConfiguration>()