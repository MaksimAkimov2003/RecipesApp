package ru.akimovmaksim.details_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.akimovmaksim.details_screen.domain.use_case.GetDetailsUseCase
import ru.akimovmaksim.details_screen.ui.navigation.DetailsRouter

internal class DetailsViewModel(
	private val getDetailsUseCase: GetDetailsUseCase,
	private val router: DetailsRouter
) : ViewModel() {

	private val _state = MutableLiveData<DetailsViewModelState>(DetailsViewModelState.Initial)
	val state: LiveData<DetailsViewModelState> = _state

	fun getRecipeDetails(recipeId: String) {
		viewModelScope.launch {
			try {
				val result = getDetailsUseCase.invoke(recipeId)
				_state.value = DetailsViewModelState.Content(result)
			} catch (e: Throwable) {
				_state.value = DetailsViewModelState.ConnectionError
			}
		}
	}

	fun refreshScreen(id: String) {
		router.refreshScreen(id)
	}

	fun navigateBack() {
		router.exit()
	}

	fun navigateToImageScreen(url: String) {
		router.navigateToImageScreen(url)
	}
}