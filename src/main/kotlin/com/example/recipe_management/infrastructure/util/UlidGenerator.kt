package com.example.recipe_management.infrastructure.util

import com.github.f4b6a3.ulid.Ulid
import com.github.f4b6a3.ulid.UlidCreator

object UlidGenerator {
    fun generate(): Ulid {
        return UlidCreator.getUlid()
    }

    fun from(ulidStr: String): Ulid {
        return Ulid.from(ulidStr)
    }
}