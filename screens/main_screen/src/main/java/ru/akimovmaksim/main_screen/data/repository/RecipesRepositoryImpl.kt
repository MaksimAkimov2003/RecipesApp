package ru.akimovmaksim.main_screen.data.repository

import ru.akimovmaksim.main_screen.data.data_source.RecipesDataSource
import ru.akimovmaksim.main_screen.data.mapper.toEntity
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity
import ru.akimovmaksim.main_screen.domain.repository.RecipesRepository

class RecipesRepositoryImpl(
	private val dataSource: RecipesDataSource
) : RecipesRepository {

	override suspend fun getRecipesFromNetwork(): List<RecipeEntity> {
		return dataSource.getRecipes().toEntity()
	}
}