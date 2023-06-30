package ru.akimovmaksim.details_screen.data.data_source

import ru.akimovmaksim.details_screen.data.model.DetailsModel

internal interface DetailsDataSource {

	suspend fun getDetails(recipeId: String): DetailsModel
}