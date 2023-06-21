package ru.akimovmaksim.main_screen.data.mapper

import ru.akimovmaksim.main_screen.data.model.RecipeModel
import ru.akimovmaksim.main_screen.data.model.RecipesListModel
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

fun RecipeModel.toEntity() = RecipeEntity(
	id, name, images, lastUpdated, description, instruction, difficulty
)

fun RecipesListModel.toEntity(): List<RecipeEntity> {
	return recipes.map {
		it.toEntity()
	}
}