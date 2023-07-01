package ru.akimovmaksim.details_screen.presentation

import ru.akimovmaksim.details_screen.domain.entity.DetailsEntity

internal sealed class DetailsViewModelState {
	object Initial : DetailsViewModelState()
	object Loading : DetailsViewModelState()
	data class Content(
		val details: DetailsEntity
	) : DetailsViewModelState()

	object ConnectionError : DetailsViewModelState()
}
