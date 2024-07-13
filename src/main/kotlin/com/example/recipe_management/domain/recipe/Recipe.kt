package com.example.recipe_management.domain.recipe

import com.github.f4b6a3.ulid.Ulid

data class Recipe(
    val id: Ulid,
    val name: String,
    val description: String,
    val ingredients: List<Ingredient> = listOf()
) {
    fun addIngredient(ingredient: Ingredient): Recipe {
        return this.copy(ingredients = ingredients + ingredient)
    }
}