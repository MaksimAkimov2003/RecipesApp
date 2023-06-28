package ru.akimovmaksim.main_screen.domain.use_case

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

class SearchRecipesUseCase {

	suspend fun invoke(
		word: String,
		recipes: List<RecipeEntity>
	): List<RecipeEntity>? {
		val matches = mutableListOf<RecipeEntity>()

		for (recipe in recipes) {
			if (recipe.name.contains(word)) {
				matches.add(recipe)
			}

			recipe.description?.let {
				if (it.contains(word)) {
					matches.add(recipe)
				}
			}

			if (recipe.instruction.contains(word)) {
				matches.add(recipe)
			}
		}

		if (matches.isEmpty()) return null

		return matches
	}
}