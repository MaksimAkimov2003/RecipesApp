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
import ru.akimovmaksim.main_screen.ui.navigation.RecipesRouter

class RecipesViewModel(
	private val getRecipesUseCase: GetRecipesUseCase,
	private val sortByDateUseCase: SortByDateUseCase,
	private val sortByNameUseCase: SortByNameUseCase,
	private val searchRecipesUseCase: SearchRecipesUseCase,
	private val router: RecipesRouter
) : ViewModel() {

	private var fullRecipes: List<RecipeEntity>? = null
	private var currentSearchResults: List<RecipeEntity>? = null

	private val _state = MutableLiveData<RecipesViewModelState>(RecipesViewModelState.Initial)
	val state: LiveData<RecipesViewModelState> = _state

	fun loadRecipes() {
		_state.value = RecipesViewModelState.Loading
		viewModelScope.launch {
			val recipes = getRecipesUseCase.invoke()

			recipes.onSuccess {
				fullRecipes = it
				currentSearchResults = it
				_state.value = RecipesViewModelState.Content(it)
			}

			recipes.onFailure {
				_state.value = RecipesViewModelState.ConnectionError
			}
		}
	}

	fun sortRecipesByName() {
		currentSearchResults?.let {
			val sortedRecipes = sortByNameUseCase.invoke(it)
			_state.value = RecipesViewModelState.Content(sortedRecipes)
		}
	}

	fun sortRecipesByDate() {
		currentSearchResults?.let {
			val sortedRecipes = sortByDateUseCase.invoke(it)
			_state.value = RecipesViewModelState.Content(sortedRecipes)
		}
	}

	fun sortRecipesByDefault() {
		currentSearchResults?.let {
			_state.value = RecipesViewModelState.Content(recipes = it)
		}
	}

	fun searchRecipe(request: String) {
		fullRecipes?.let {
			val searchResult = searchRecipesUseCase.invoke(
				request = request,
				recipes = it
			)

			searchResult?.let { result ->
				currentSearchResults = result.toList()
				_state.value = RecipesViewModelState.Content(result.toList())
				return
			}

			currentSearchResults = null
			_state.value = RecipesViewModelState.EmptyContentState
		}
	}

	fun setFullRecipesList() {
		fullRecipes?.let {
			currentSearchResults = it
			_state.value = RecipesViewModelState.Content(it)
		}
	}

	fun navigateToDetailsScreen(id: String) {
		router.navigateToDetailsScreen(id)
	}
}
