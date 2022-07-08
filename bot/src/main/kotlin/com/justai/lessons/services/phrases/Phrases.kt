package com.justai.lessons.services.phrases

data class Phrase(val name: String)

object Phrases {
    val buy = Phrase("Buy")
    val delivery = Phrase("Delivery")
    val bye = Phrase("Bye")
    val operator = Phrase("Operator")
}