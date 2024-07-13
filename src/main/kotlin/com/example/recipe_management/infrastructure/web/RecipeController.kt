package com.example.recipe_management.infrastructure.web

import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.usecase.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/recipes")
class RecipeController @Autowired constructor(
    private val createRecipeUseCase: CreateRecipeUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val deleteRecipeUseCase: DeleteRecipeUseCase,
    private val addIngredientToRecipeUseCase: AddIngredientToRecipeUseCase
) {

    @PostMapping
    fun createRecipe(@RequestBody recipeDto: RecipeDto): ResponseEntity<Recipe> {
        return ResponseEntity.ok(createRecipeUseCase.execute(recipeDto))
    }

    @PutMapping("/{id}")
    fun updateRecipe(@PathVariable id: String, @RequestBody recipeDto: RecipeDto): ResponseEntity<Recipe> {
        return ResponseEntity.ok(updateRecipeUseCase.execute(id, recipeDto))
    }

    @DeleteMapping("/{id}")
    fun deleteRecipe(@PathVariable id: String): ResponseEntity<Void> {
        deleteRecipeUseCase.execute(id)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{id}/ingredients")
    fun addIngredient(@PathVariable id: String, @RequestBody ingredientDto: IngredientDto): ResponseEntity<Recipe> {
        val updatedRecipe = addIngredientToRecipeUseCase.execute(id, ingredientDto)
        return ResponseEntity.ok(updatedRecipe)
    }


}