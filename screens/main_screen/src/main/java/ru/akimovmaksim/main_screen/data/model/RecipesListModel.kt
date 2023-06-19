package ru.akimovmaksim.main_screen.data.model

import com.google.gson.annotations.SerializedName

data class RecipesListModel(
	@SerializedName("recipes")
	val recipes: List<RecipeModel>
)
