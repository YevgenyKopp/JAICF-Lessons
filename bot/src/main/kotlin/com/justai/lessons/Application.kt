package com.justai.lessons

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan("com.justai.lessons")
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
