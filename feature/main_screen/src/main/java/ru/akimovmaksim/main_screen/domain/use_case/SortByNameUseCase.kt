package ru.akimovmaksim.main_screen.domain.use_case

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

class SortByNameUseCase {

	fun invoke(recipes: List<RecipeEntity>): List<RecipeEntity> {
		return recipes.sortedBy { it.name }
	}
}