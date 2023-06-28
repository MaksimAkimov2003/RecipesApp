package ru.akimovmaksim.main_screen.domain.use_case

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

class SortByDateUseCase {

	fun invoke(recipes: List<RecipeEntity>): List<RecipeEntity> {
		return recipes.sortedBy {
			it.lastUpdated
		}
	}
}