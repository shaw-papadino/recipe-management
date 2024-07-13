package com.example.recipe_management.usecase

import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.domain.recipe.RecipeRepository
import com.example.recipe_management.infrastructure.util.UlidGenerator
import org.springframework.stereotype.Service

@Service
class UpdateRecipeUseCase(private val recipeRepository: RecipeRepository) {
    fun execute(recipeId: String, updatedRecipeDto: RecipeDto): Recipe {
        val id = UlidGenerator.from(recipeId)
        val recipeToUpdate = recipeRepository.findById(id)
            ?: throw IllegalArgumentException("Recipe with id $id not found.")

        return recipeRepository.save(recipeToUpdate.copy(name = updatedRecipeDto.name, description = updatedRecipeDto.description))
    }
}