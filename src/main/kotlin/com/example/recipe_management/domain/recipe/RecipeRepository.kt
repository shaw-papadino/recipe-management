package com.example.recipe_management.domain.recipe

import com.github.f4b6a3.ulid.Ulid

interface RecipeRepository {
    fun findById(id: Ulid): Recipe?
    fun save(recipe: Recipe): Recipe
    fun deleteByID(id: Ulid)
}