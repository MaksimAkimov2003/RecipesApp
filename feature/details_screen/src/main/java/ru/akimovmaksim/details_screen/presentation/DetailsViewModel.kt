package ru.akimovmaksim.details_screen.presentation

import androidx.lifecycle.ViewModel
import ru.akimovmaksim.details_screen.domain.use_case.GetDetailsUseCase

internal class DetailsViewModel(
	private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

}