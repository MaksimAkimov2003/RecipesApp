package ru.akimovmaksim.details_screen.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import ru.akimovmaksim.details_screen.data.model.DetailsModel

interface DetailsService {

	@GET("/recipes/{id}")
	suspend fun getDetails(
		@Path("id")
		recipeId: String
	): DetailsModel
}