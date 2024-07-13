package com.example.recipe_management.usecase

import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.domain.recipe.RecipeRepository
import com.example.recipe_management.infrastructure.util.UlidGenerator
import com.github.f4b6a3.ulid.Ulid
import org.springframework.stereotype.Service

@Service
class DeleteRecipeUseCase(private val recipeRepository: RecipeRepository) {
    fun execute(recipeId: String): Ulid {
        val id = UlidGenerator.from(recipeId)
        if (recipeRepository.findById(id) == null) {
            throw IllegalArgumentException("Recipe with id $recipeId not found.")
        }

        recipeRepository.deleteByID(id)
        return id
    }
}