package ru.akimovmaksim.main_screen.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.akimovmaksim.main_screen.domain.use_case.GetRecipesUseCase

class RecipesViewModel(
	private val getRecipesUseCase: GetRecipesUseCase
) : ViewModel() {

	fun getRecipes() {
		viewModelScope.launch {
			getRecipesUseCase.invoke()
		}
	}

}
