package ru.akimovmaksim.details_screen.domain.entity

internal data class DetailsEntity(
	val id: String,
	val name: String,
	val images: List<String>,
	val lastUpdated: Int,
	val description: String?,
	val instructions: String,
	val difficulty: Int,
	val similarRecipes: List<SimilarEntity>
)