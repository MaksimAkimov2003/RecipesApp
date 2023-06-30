package ru.akimovmaksim.details_screen.data.mapper

import ru.akimovmaksim.details_screen.data.model.DetailsModel
import ru.akimovmaksim.details_screen.data.model.SimilarModel
import ru.akimovmaksim.details_screen.domain.entity.DetailsEntity
import ru.akimovmaksim.details_screen.domain.entity.SimilarEntity

internal fun SimilarModel.toEntity() = SimilarEntity(
	id, name, poster
)

internal fun List<SimilarModel>.toEntity(): List<SimilarEntity> {
	return map { it.toEntity() }
}

internal fun DetailsModel.toEntity(): DetailsEntity {
	return with(recipe) {
		DetailsEntity(
			id = id,
			name = name,
			images = images,
			lastUpdated = lastUpdated,
			description = description,
			instructions = instructions,
			difficulty = difficulty,
			similarRecipes = similarRecipes.toEntity()
		)
	}
}
