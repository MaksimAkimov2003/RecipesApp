package ru.akimovmaksim.details_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarteist.autoimageslider.SliderView
import org.koin.android.ext.android.inject
import ru.akimovmaksim.details_screen.R
import ru.akimovmaksim.details_screen.databinding.DetailsFragmentBinding
import ru.akimovmaksim.details_screen.presentation.DetailsViewModel
import ru.akimovmaksim.details_screen.presentation.DetailsViewModelState
import ru.akimovmaksim.details_screen.ui.recycler.SimilarAdapter
import ru.akimovmaksim.details_screen.ui.slider.SliderAdapter
import ru.akimovmaksim.ui.hideLoadingState
import ru.akimovmaksim.ui.showConnectionErrorState
import ru.akimovmaksim.ui.showLoadingState

class DetailsFragment : Fragment(R.layout.details_fragment) {
	companion object {

		private const val DETAILS_FRAGMENT_KEY = "DETAILS_FRAGMENT_KEY"
		private const val SCROLL_TIME = 3

		fun newInstance(id: String) = DetailsFragment().apply {
			val bundle = Bundle()
			bundle.putString(DETAILS_FRAGMENT_KEY, id)
			arguments = bundle
		}
	}

	private lateinit var binding: DetailsFragmentBinding

	private val viewModel by inject<DetailsViewModel>()
	private val recyclerAdapter by lazy { SimilarAdapter(::onItemClick) }

	private fun onItemClick(id: String) {
		viewModel.refreshScreen(id)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = DetailsFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObserves()
		setAdapters()
		setListeners()
		getDetails()
	}

	private fun setListeners() {
		binding.backButton.setOnClickListener { viewModel.navigateBack() }
	}

	private fun setAdapters() {
		binding.similarRecycler.adapter = recyclerAdapter
	}

	private fun setObserves() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: DetailsViewModelState) {
		when (state) {
			is DetailsViewModelState.Initial         -> Unit
			is DetailsViewModelState.Loading         ->
				showLoadingState(
					content = binding.content,
					progressBar = binding.progressBar
				)

			is DetailsViewModelState.Content         -> showContentState(state)

			is DetailsViewModelState.ConnectionError -> {
				hideLoadingState(binding.content, binding.progressBar)
				context?.showConnectionErrorState(
					content = binding.content,
					progressBar = binding.progressBar,
					message = getString(ru.akimovmaksim.resources.R.string.error_message)
				)
			}
		}
	}

	private fun getDetails() {
		arguments?.getString(DETAILS_FRAGMENT_KEY)?.let {
			viewModel.getRecipeDetails(it)
		}
	}

	private fun showContentState(state: DetailsViewModelState.Content) {
		binding.let {
			hideLoadingState(it.content, it.progressBar)
			it.tittle.text = state.details.name
			it.description.text = state.details.description
			it.ratingBar.rating = state.details.difficulty.toFloat()
			it.instruction.text = state.details.instructions
			it.picturesCount.text = buildString {
				append("pictures count: ")
				append(state.details.images.size)
			}
		}
		showSlider(state.details.images)
		showRecycler(state)
	}

	private fun showRecycler(state: DetailsViewModelState.Content) {
		recyclerAdapter.submitList(state.details.similarRecipes)
	}

	private fun showSlider(images: List<String>) {
		val sliderAdapter = SliderAdapter(images, ::onImageClick)

		with(binding.slider) {
			autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
			setSliderAdapter(sliderAdapter)
			scrollTimeInSec = SCROLL_TIME
			isAutoCycle = true
			startAutoCycle()
		}
	}

	private fun onImageClick(imageUrl: String) {
		viewModel.navigateToImageScreen(imageUrl)
	}
}