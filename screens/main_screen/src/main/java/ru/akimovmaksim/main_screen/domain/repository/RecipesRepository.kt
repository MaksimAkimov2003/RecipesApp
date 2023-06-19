package ru.akimovmaksim.main_screen.domain.repository

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

interface RecipesRepository {

	suspend fun getRecipesFromNetwork(): List<RecipeEntity>
}