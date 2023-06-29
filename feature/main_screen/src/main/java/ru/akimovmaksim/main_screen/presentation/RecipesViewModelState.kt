package ru.akimovmaksim.main_screen.presentation

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

sealed class RecipesViewModelState {
	object Initial : RecipesViewModelState()
	object Loading : RecipesViewModelState()
	data class Content(
		val recipes: List<RecipeEntity>
	) : RecipesViewModelState()

	object ConnectionError : RecipesViewModelState()

	object EmptyContentState : RecipesViewModelState()
}