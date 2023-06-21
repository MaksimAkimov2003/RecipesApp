package ru.akimovmaksim.main_screen.data.data_source

import ru.akimovmaksim.main_screen.data.api.RecipesService
import ru.akimovmaksim.main_screen.data.model.RecipesListModel

class RecipesDataSourceImpl constructor(
	private val api: RecipesService
) : RecipesDataSource {

	override suspend fun getRecipes(): RecipesListModel {
		return api.getRecipes()
	}
}