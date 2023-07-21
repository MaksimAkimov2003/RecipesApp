package ru.akimovmaksim.details_screen.domain.use_case

import ru.akimovmaksim.details_screen.domain.entity.DetailsEntity
import ru.akimovmaksim.details_screen.domain.repository.DetailsRepository

internal class GetDetailsUseCase(
	private val repository: DetailsRepository
) {

	suspend fun invoke(recipeId: String): Result<DetailsEntity> {
		return repository.runCatching {
			this.searchDetailsFromApi(recipeId)
		}
	}
}