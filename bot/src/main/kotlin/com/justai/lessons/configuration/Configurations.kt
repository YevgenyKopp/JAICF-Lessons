package com.justai.lessons.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("phrases")
class PhrasesConfiguration {
    var content: Map<String, Phrase>? = null

    class Phrase {
        var default: List<String>? = null
        var fallback: List<String>? = null
    }
}

@ConstructorBinding
@ConfigurationProperties(prefix = "some-config")
class SomeConfiguration {
    var enabled: Boolean? = null
    var text: String? = null
}