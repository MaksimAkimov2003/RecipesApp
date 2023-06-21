package ru.akimovmaksim.main_screen.domain.entity

data class RecipeEntity(
	val id: String,
	val name: String,
	val images: List<String>,
	val lastUpdated: Int,
	val description: String?,
	val instruction: String,
	val difficulty: Int
)
