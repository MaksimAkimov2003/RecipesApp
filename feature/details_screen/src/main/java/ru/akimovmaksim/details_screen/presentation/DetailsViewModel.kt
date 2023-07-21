package ru.akimovmaksim.details_screen.presentation

import android.util.Log
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
		_state.value = DetailsViewModelState.Loading
		viewModelScope.launch {
			val result = getDetailsUseCase.invoke(recipeId)

			result.onSuccess {
				_state.value = DetailsViewModelState.Content(it)
				Log.e("STATE VALUE", _state.value.toString())
			}

			result.onFailure {
				_state.value = DetailsViewModelState.ConnectionError
			}
		}
	}

	fun refreshScreen(id: String) {
		router.navigateToDetailsScreen(id)
	}

	fun navigateBack() {
		router.exit()
	}

	fun navigateToImageScreen(url: String) {
		router.navigateToImageScreen(url)
	}
}