package ru.akimovmaksim.main_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity
import ru.akimovmaksim.main_screen.domain.use_case.GetRecipesUseCase
import ru.akimovmaksim.main_screen.domain.use_case.SearchRecipesUseCase
import ru.akimovmaksim.main_screen.domain.use_case.SortByDateUseCase
import ru.akimovmaksim.main_screen.domain.use_case.SortByNameUseCase

class RecipesViewModel(
	private val getRecipesUseCase: GetRecipesUseCase,
	private val sortByDateUseCase: SortByDateUseCase,
	private val sortByNameUseCase: SortByNameUseCase,
	private val searchRecipesUseCase: SearchRecipesUseCase
) : ViewModel() {

	private var fullRecipes: List<RecipeEntity>? = null

	private val _state = MutableLiveData<RecipesViewModelState>(RecipesViewModelState.Initial)
	val state: LiveData<RecipesViewModelState> = _state

	fun loadRecipes() {
		viewModelScope.launch {
			try {
				val recipes = getRecipesUseCase.invoke()
				_state.value = RecipesViewModelState.Content(recipes)
				fullRecipes = recipes
			} catch (e: Exception) {
				_state.value = RecipesViewModelState.ConnectionError
			}
		}
	}

	fun sortRecipesByName() {
		val currentState = _state.value as? RecipesViewModelState.Content ?: return
		val sortedRecipes = sortByNameUseCase.invoke(currentState.recipes)
		_state.value = RecipesViewModelState.Content(sortedRecipes)
	}

	fun sortRecipesByDate() {
		val currentState = _state.value as? RecipesViewModelState.Content ?: return
		val sortedRecipes = sortByDateUseCase.invoke(currentState.recipes)
		_state.value = RecipesViewModelState.Content(sortedRecipes)
	}

	fun sortRecipesByDefault() {
		_state.value = fullRecipes?.let { RecipesViewModelState.Content(recipes = it) }
	}

	suspend fun searchRecipe(word: String) {
		val currentState = _state.value as? RecipesViewModelState.Content ?: return
		val searchResult = searchRecipesUseCase.invoke(
			word = word,
			recipes = currentState.recipes
		)
	}
}
