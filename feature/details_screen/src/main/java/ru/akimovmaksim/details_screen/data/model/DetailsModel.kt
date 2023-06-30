package ru.akimovmaksim.details_screen.data.model

import com.google.gson.annotations.SerializedName

data class DetailsModel(
	@SerializedName("recipe")
	val recipe: RecipeModel
)
