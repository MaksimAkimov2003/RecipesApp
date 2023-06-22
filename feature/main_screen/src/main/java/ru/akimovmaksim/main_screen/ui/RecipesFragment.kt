package ru.akimovmaksim.main_screen.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.akimovmaksim.main_screen.R
import ru.akimovmaksim.main_screen.databinding.RecipesFragmentBinding
import ru.akimovmaksim.main_screen.presentation.RecipesViewModel
import ru.akimovmaksim.main_screen.presentation.RecipesViewModelState
import ru.akimovmaksim.main_screen.ui.recycler.RecipesAdapter

class RecipesFragment() : Fragment(R.layout.recipes_fragment) {

	private lateinit var binding: RecipesFragmentBinding

	private val viewModel by inject<RecipesViewModel>()
	private val adapter: RecipesAdapter by lazy { RecipesAdapter() }

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		binding = RecipesFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObserves()
		setAdapter()
		loadRecipesList()
	}

	private fun setAdapter() {
		binding.recipesRecycler.adapter = adapter
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
		Log.d("Content State", "Done")
		adapter.submitList(state.recipes)
	}

	private fun showConnectionErrorState() {
		Toast.makeText(context, getString(ru.akimovmaksim.resources.R.string.error_message), Toast.LENGTH_SHORT)
			.show()
	}
}