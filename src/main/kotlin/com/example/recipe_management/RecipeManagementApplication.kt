package com.example.recipe_management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RecipeManagementApplication

fun main(args: Array<String>) {
	runApplication<RecipeManagementApplication>(*args)
}
