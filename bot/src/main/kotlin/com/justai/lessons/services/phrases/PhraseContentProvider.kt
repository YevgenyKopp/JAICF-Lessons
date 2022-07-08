package com.justai.lessons.services.phrases

import com.justai.lessons.configuration.PhrasesConfiguration
import org.springframework.stereotype.Service

@Service
class PhraseContentProvider(
    private val config: PhrasesConfiguration,
) {
    fun getByPhrase(phrase: Phrase) = config.content?.get(phrase.name)
}