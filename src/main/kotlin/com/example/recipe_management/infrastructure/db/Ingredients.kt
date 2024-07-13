package com.example.recipe_management.infrastructure.db

import org.jetbrains.exposed.dao.id.IdTable

object Ingredients: IdTable<String>() {
    override val id = varchar("id", 26).entityId()
    val recipeId = varchar("recipe_id", 26).references(Recipes.id)
    val name = varchar("name", 50)
    val quantity = varchar("quantity", 20)
}