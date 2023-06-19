package ru.akimovmaksim.main_screen.data.api

import retrofit2.http.GET
import ru.akimovmaksim.main_screen.data.model.RecipesListModel

interface RecipesService {

	@GET("/recipes")
	suspend fun getRecipes(): RecipesListModel
}