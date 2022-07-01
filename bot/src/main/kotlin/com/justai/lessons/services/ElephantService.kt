package com.justai.lessons.services

import org.springframework.stereotype.Service

@Service
class ElephantService {
    fun isAvailable(elephant: String) = (1..10).random() > 5
}