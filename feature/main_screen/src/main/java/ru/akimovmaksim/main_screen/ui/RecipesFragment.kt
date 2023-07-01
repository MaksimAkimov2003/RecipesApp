package ru.akimovmaksim.main_screen.ui

import android.content.Context
import android.content.res.Resources.*
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.akimovmaksim.main_screen.R
import ru.akimovmaksim.main_screen.databinding.RecipesFragmentBinding
import ru.akimovmaksim.main_screen.presentation.RecipesViewModel
import ru.akimovmaksim.main_screen.presentation.RecipesViewModelState
import ru.akimovmaksim.main_screen.ui.recycler.RecipesAdapter
import ru.akimovmaksim.ui.showConnectionErrorState
import ru.akimovmaksim.ui.showLoadingState

class RecipesFragment : Fragment(R.layout.recipes_fragment), AdapterView.OnItemSelectedListener, TextView.OnEditorActionListener {

	private companion object {

		const val BY_DEFAULT_TAG = "BY_DEFAULT"
		const val BY_NAME_TAG = "BY_NAME"
		const val BY_DATE_TAG = "BY_DATE"
	}

	private lateinit var binding: RecipesFragmentBinding

	private val viewModel by inject<RecipesViewModel>()
	private val adapter: RecipesAdapter by lazy { RecipesAdapter(::navigateToDetailsScreen) }

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

	private fun navigateToDetailsScreen(id: String) {
		viewModel.navigateToDetailsScreen(id)
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
		setListeners()
	}

	private fun setListeners() {
		binding.run {
			searchField.setOnEditorActionListener(this@RecipesFragment)
			searchLayout.setEndIconOnClickListener {
				searchField.text = null
				viewModel.setFullRecipesList()
				spinner.setSelection(0, false)
			}
		}
	}

	private fun setAdapters() {
		with(binding) {
			recipesRecycler.adapter = adapter
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
			spinner.adapter = spinnerAdapter
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
			is RecipesViewModelState.Initial           -> Unit
			is RecipesViewModelState.Loading           -> showLoadingState(
				content = binding.content,
				progressBar = binding.progressBar
			)
			is RecipesViewModelState.Content           -> showContentState(state)
			is RecipesViewModelState.ConnectionError   -> showConnectionErrorState(
				content = binding.content,
				progressBar = binding.progressBar,
				context = context,
				message = getString(ru.akimovmaksim.resources.R.string.error_message)
			)
			is RecipesViewModelState.EmptyContentState -> showEmptyContentState()
		}
	}

	private fun showEmptyContentState() {
		with(binding) {
			content.isVisible = true
			progressBar.isVisible = false
			recipesRecycler.isVisible = false
		}
		Toast.makeText(context, getString(ru.akimovmaksim.resources.R.string.empty_content), Toast.LENGTH_SHORT).show()
	}

	private fun showContentState(state: RecipesViewModelState.Content) {
		with(binding) {
			content.isVisible = true
			progressBar.isVisible = false
			recipesRecycler.isVisible = true
		}
		adapter.submitList(state.recipes)
		setItemSelectedListener()
	}

	private fun showConnectionErrorStat() {
		with(binding) {
			content.isVisible = true
			progressBar.isVisible = false
		}
		Toast.makeText(context, getString(ru.akimovmaksim.resources.R.string.error_message), Toast.LENGTH_SHORT).show()
	}

	private fun setItemSelectedListener() {
		with(binding) {
			spinner.onItemSelectedListener = this@RecipesFragment
		}
	}

	override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
		when (sortTypes.keys.toList()[position]) {
			BY_DEFAULT_TAG -> {
				viewModel.sortRecipesByDefault()
				scrollListToTop()
			}

			BY_NAME_TAG    -> {
				viewModel.sortRecipesByName()
				scrollListToTop()
			}

			BY_DATE_TAG    -> {
				viewModel.sortRecipesByDate()
				scrollListToTop()
			}
		}
	}

	override fun onNothingSelected(parent: AdapterView<*>?) {
		throw NotFoundException("Selected item not found")
	}

	override fun onEditorAction(p0: TextView?, actionId: Int, p2: KeyEvent?): Boolean {
		if (actionId == EditorInfo.IME_ACTION_SEARCH) {
			hideKeyboard()
			viewModel.searchRecipe(binding.searchField.text.toString())
			binding.spinner.setSelection(0, false)
			return true
		}
		return false
	}

	private fun hideKeyboard() {
		activity?.currentFocus?.let { view ->
			val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
			imm?.hideSoftInputFromWindow(view.windowToken, 0)
		}
	}

	private fun scrollListToTop() {
		binding.recipesRecycler.post {
			binding.recipesRecycler.scrollToPosition(0)
		}
	}
}