package ru.akimovmaksim.details_screen.data.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
	@SerializedName("uuid")
	val id: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("images")
	val images: List<String>,

	@SerializedName("lastUpdated")
	val lastUpdated: Int,

	@SerializedName("description")
	val description: String?,

	@SerializedName("instructions")
	val instructions: String,

	@SerializedName("difficulty")
	val difficulty: Int,

	@SerializedName("similar")
	val similarRecipes: List<SimilarModel>
)

