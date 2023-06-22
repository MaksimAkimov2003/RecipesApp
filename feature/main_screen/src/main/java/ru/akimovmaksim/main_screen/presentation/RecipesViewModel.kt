package ru.akimovmaksim.main_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.akimovmaksim.main_screen.domain.use_case.GetRecipesUseCase

class RecipesViewModel(
	private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

	private val _state = MutableLiveData<RecipesViewModelState>(RecipesViewModelState.Initial)
	val state: LiveData<RecipesViewModelState> = _state

	fun loadRecipes() {
		viewModelScope.launch {
			try {
				val recipes = getRecipesUseCase.invoke()
				_state.value = RecipesViewModelState.Content(recipes)
			} catch (e: Exception) {
				_state.value = RecipesViewModelState.ConnectionError
			}
		}
	}

}
