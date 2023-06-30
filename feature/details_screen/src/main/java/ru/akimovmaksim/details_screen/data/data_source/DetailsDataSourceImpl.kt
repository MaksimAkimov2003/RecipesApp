package ru.akimovmaksim.details_screen.data.data_source

import ru.akimovmaksim.details_screen.data.api.DetailsService
import ru.akimovmaksim.details_screen.data.model.DetailsModel

internal class DetailsDataSourceImpl(
	private val api: DetailsService
) : DetailsDataSource {

	override suspend fun getDetails(recipeId: String): DetailsModel {
		return api.getDetails(recipeId)
	}
}