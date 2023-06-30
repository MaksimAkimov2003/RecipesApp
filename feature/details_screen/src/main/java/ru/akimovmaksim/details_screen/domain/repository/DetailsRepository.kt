package ru.akimovmaksim.details_screen.domain.repository

import ru.akimovmaksim.details_screen.domain.entity.DetailsEntity

internal interface DetailsRepository {

	suspend fun searchDetailsFromApi(recipeId: String): DetailsEntity
}