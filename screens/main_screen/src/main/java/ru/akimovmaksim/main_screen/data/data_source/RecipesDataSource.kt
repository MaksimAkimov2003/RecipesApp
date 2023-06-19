package ru.akimovmaksim.main_screen.data.data_source

import ru.akimovmaksim.main_screen.data.model.RecipesListModel

interface RecipesDataSource {

	suspend fun getRecipes(): RecipesListModel
}