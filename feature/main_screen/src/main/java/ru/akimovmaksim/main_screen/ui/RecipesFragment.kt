package ru.akimovmaksim.main_screen.ui

import android.content.res.Resources.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import ru.akimovmaksim.main_screen.R
import ru.akimovmaksim.main_screen.databinding.RecipesFragmentBinding
import ru.akimovmaksim.main_screen.presentation.RecipesViewModel
import ru.akimovmaksim.main_screen.presentation.RecipesViewModelState
import ru.akimovmaksim.main_screen.ui.recycler.RecipesAdapter

class RecipesFragment : Fragment(R.layout.recipes_fragment), AdapterView.OnItemSelectedListener {

	private companion object {

		const val BY_DEFAULT_TAG = "BY_DEFAULT"
		const val BY_NAME_TAG = "BY_NAME"
		const val BY_DATE_TAG = "BY_DATE"
	}

	private lateinit var binding: RecipesFragmentBinding

	private val viewModel by inject<RecipesViewModel>()
	private val adapter: RecipesAdapter by lazy { RecipesAdapter() }

	private val sortTypes: Map<String, String> by lazy {
		mapOf(
			BY_DEFAULT_TAG to getString(ru.akimovmaksim.resources.R.string.by_default),
			BY_NAME_TAG to getString(ru.akimovmaksim.resources.R.string.by_name),
			BY_DATE_TAG to getString(ru.akimovmaksim.resources.R.string.by_date),
		)
	}

	private val spinnerAdapter: ArrayAdapter<String> by lazy {
		ArrayAdapter(
			requireContext(), android.R.layout.simple_spinner_item, sortTypes.values.toList()
		)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		binding = RecipesFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObserves()
		loadRecipesList()
		setAdapters()

	}

	private fun setListeners() {
		binding.searchField.doOnTextChanged { word, _, _, _ ->
			viewLifecycleOwner.lifecycleScope.launch {
				delay(1000)
				viewModel.searchRecipe(word.toString())
			}
		}
	}

	private fun setAdapters() {
		with(binding) {
			recipesRecycler.adapter = adapter

			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
			spinner.adapter = spinnerAdapter
			spinner.onItemSelectedListener = this@RecipesFragment
		}
	}

	private fun loadRecipesList() {
		viewModel.loadRecipes()
	}

	private fun setObserves() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: RecipesViewModelState) {
		when (state) {
			is RecipesViewModelState.Initial         -> Unit
			is RecipesViewModelState.Content         -> showContentState(state)
			is RecipesViewModelState.ConnectionError -> showConnectionErrorState()
		}
	}

	private fun showContentState(state: RecipesViewModelState.Content) {
		Log.d("showContentState", binding.recipesRecycler.verticalScrollbarPosition.toString())
		adapter.submitList(state.recipes)
	}

	private fun showConnectionErrorState() {
		Toast.makeText(context, getString(ru.akimovmaksim.resources.R.string.error_message), Toast.LENGTH_SHORT).show()
	}

	override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
		when (sortTypes.keys.toList()[position]) {
			BY_DEFAULT_TAG -> {
				Log.d("Spinner", "Default")
//				viewModel.sortRecipesByDefault()
			}

			BY_NAME_TAG    -> {
				Log.d("Spinner", "Name")
				viewModel.sortRecipesByName()
			}

			BY_DATE_TAG    -> {
				Log.d("Spinner", "Date")
				viewModel.sortRecipesByDate()
			}
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>?) {
		throw NotFoundException("Selected item not found")
	}
}