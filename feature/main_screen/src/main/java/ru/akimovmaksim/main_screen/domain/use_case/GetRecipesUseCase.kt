package ru.akimovmaksim.main_screen.domain.use_case

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity
import ru.akimovmaksim.main_screen.domain.repository.RecipesRepository

class GetRecipesUseCase(
	private val repository: RecipesRepository
) {

	suspend fun invoke(): List<RecipeEntity> {
		return repository.getRecipesFromNetwork()
	}
}