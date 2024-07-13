package com.example.recipe_management.infrastructure.db

import com.example.recipe_management.domain.recipe.Ingredient
import com.example.recipe_management.domain.recipe.Recipe
import com.example.recipe_management.domain.recipe.RecipeRepository
import com.example.recipe_management.infrastructure.util.UlidGenerator
import com.github.f4b6a3.ulid.Ulid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class RecipeRepositoryImpl: RecipeRepository {
    override fun findById(id: Ulid): Recipe? {
       return transaction {
           val recipeRow = Recipes.select { Recipes.id eq id.toString() }.singleOrNull()
           recipeRow?.let {
               val ingredients = Ingredients.select { Ingredients.recipeId eq it[Recipes.id].value }
                   .map { ingredientRow -> convertToIngredient(ingredientRow) }
               convertToRecipe(it, ingredients)
           }
       }
    }

    override fun save(recipe: Recipe): Recipe {
        val existingRecipe = findById(recipe.id)

        val id = transaction {
            if (existingRecipe == null) {
                Recipes.insert {
                    it[id] = recipe.id.toString()
                    it[name] = recipe.name
                    it[description] = recipe.description
                }
                recipe.id
            } else {
                Recipes.update ({ Recipes.id eq recipe.id.toString() }) {
                    it[name] = recipe.name
                    it[description] = recipe.description
                }
                recipe.id
            }
        }

        transaction {
            Ingredients.deleteWhere { Ingredients.recipeId eq id.toString() }

            recipe.ingredients.forEach { ingredient ->
                Ingredients.insert {
                    it[recipeId] = id.toString()
                    it[name] = ingredient.name
                    it[quantity] = ingredient.quantity
                }
            }
        }
        return findById(id) ?: throw IllegalStateException("Recipe not found after saved.")
    }

    override fun deleteByID(id: Ulid) {
        transaction {
            Ingredients.deleteWhere { Ingredients.recipeId eq id.toString() }
            Recipes.deleteWhere { Recipes.id eq id.toString() }
        }
    }

    private fun convertToRecipe(row: ResultRow, ingredients: List<Ingredient>): Recipe =
            Recipe(
                UlidGenerator.from(row[Recipes.id].value),
                row[Recipes.name],
                row[Recipes.description],
                ingredients
            )

    private fun convertToIngredient(row: ResultRow): Ingredient =
        Ingredient(row[Ingredients.name], row[Ingredients.quantity])

}