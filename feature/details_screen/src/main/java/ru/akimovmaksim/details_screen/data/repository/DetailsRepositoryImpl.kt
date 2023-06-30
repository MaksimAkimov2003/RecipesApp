package ru.akimovmaksim.details_screen.data.repository

import ru.akimovmaksim.details_screen.data.data_source.DetailsDataSource
import ru.akimovmaksim.details_screen.data.mapper.toEntity
import ru.akimovmaksim.details_screen.domain.entity.DetailsEntity
import ru.akimovmaksim.details_screen.domain.repository.DetailsRepository

internal class DetailsRepositoryImpl(
	private val dataSource: DetailsDataSource
) : DetailsRepository {

	override suspend fun searchDetailsFromApi(recipeId: String): DetailsEntity {
		return dataSource.getDetails(recipeId).toEntity()
	}
}