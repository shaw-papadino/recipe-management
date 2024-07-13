package com.example.recipe_management.infrastructure.db

import jakarta.annotation.PostConstruct
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {

    @Bean
    fun connectDatabase(): Database {
        return Database.connect(
            url = "jdbc:sqlite:./data/mydatabase.db",
            driver = "org.sqlite.JDBC"
        )
    }

    @Bean
    fun initializeDatabase(): CommandLineRunner {
        return CommandLineRunner {
            transaction(connectDatabase()) {
                SchemaUtils.create(Recipes, Ingredients)
            }
        }
    }}