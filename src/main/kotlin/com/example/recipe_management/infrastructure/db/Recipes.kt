package com.example.recipe_management.infrastructure.db

import org.jetbrains.exposed.dao.id.IdTable

object Recipes: IdTable<String>() {
    override val id = varchar("id", 26).entityId()
    val name = varchar("name", 50)
    val description = varchar("description", 140)
}