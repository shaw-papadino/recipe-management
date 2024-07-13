package com.example.recipe_management.usecase

import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.domain.recipe.RecipeRepository
import com.example.recipe_management.infrastructure.util.UlidGenerator
import org.springframework.stereotype.Service

@Service
class CreateRecipeUseCase(private val recipeRepository: RecipeRepository) {
    fun execute(recipeDto: RecipeDto): Recipe {
        val id = UlidGenerator.generate()
        if (recipeRepository.findById(id) != null) {
            throw IllegalArgumentException("Recipe with id $id already exists.")
        }
        return recipeRepository.save(Recipe(id, recipeDto.name, recipeDto.description))

    }
}