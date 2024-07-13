package com.example.recipe_management.usecase

import com.example.recipe_management.domain.recipe.Ingredient
import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.domain.recipe.RecipeRepository
import com.example.recipe_management.infrastructure.util.UlidGenerator
import org.springframework.stereotype.Service

@Service
class AddIngredientToRecipeUseCase(private val recipeRepository: RecipeRepository) {
    fun execute(recipeId: String, ingredientDto: IngredientDto): Recipe {
        val recipe = recipeRepository.findById(UlidGenerator.from(recipeId))
            ?: throw IllegalArgumentException("Recipe with id $recipeId not found.")

        val updatedRecipe = recipe.addIngredient(Ingredient(ingredientDto.name, ingredientDto.quantity))

        return recipeRepository.save(updatedRecipe)


    }
}