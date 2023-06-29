package ru.akimovmaksim.main_screen.domain.use_case

import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

class SearchRecipesUseCase {

	fun invoke(
		request: String,
		recipes: List<RecipeEntity>
	): Set<RecipeEntity>? {
		val matches = mutableSetOf<RecipeEntity>()

		for (recipe in recipes) {
			if (recipe.name.contains(request)) {
				matches.add(recipe)
			}

			recipe.description?.let {
				if (it.contains(request)) {
					matches.add(recipe)
				}
			}

			if (recipe.instruction.contains(request)) {
				matches.add(recipe)
			}
		}

		if (matches.isEmpty()) return null

		return matches
	}
}