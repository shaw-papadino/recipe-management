package com.example.recipe_management.infrastructure.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(value = [Exception::class])
    fun handlerException(ex: Exception): ResponseEntity<Any> {
        return when (ex) {
            is IllegalArgumentException -> ResponseEntity.badRequest().body(ex.message)
            else -> ResponseEntity.internalServerError().body(ex.message)
        }
    }
}