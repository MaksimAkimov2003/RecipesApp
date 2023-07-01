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
import ru.akimovmaksim.details_screen.ui.slider.SliderAdapter

class DetailsFragment : Fragment(R.layout.details_fragment) {
	companion object {

		private const val DETAILS_FRAGMENT_KEY = "DETAILS_FRAGMENT_KEY"

		fun newInstance(id: String) = DetailsFragment().apply {
			val bundle = Bundle()
			bundle.putString(DETAILS_FRAGMENT_KEY, id)
			arguments = bundle
		}
	}

	private lateinit var binding: DetailsFragmentBinding

	private val viewModel by inject<DetailsViewModel>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		binding = DetailsFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setObserves()
		getDetails()
	}

	private fun setObserves() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: DetailsViewModelState) {
		when (state) {
			is DetailsViewModelState.Initial         -> Unit
			is DetailsViewModelState.Loading         -> Unit
			is DetailsViewModelState.Content         -> showContentState(state)
			is DetailsViewModelState.ConnectionError -> Unit
		}
	}

	private fun getDetails() {
		arguments?.getString(DETAILS_FRAGMENT_KEY)?.let {
			viewModel.getRecipeDetails(it)
		}
	}

	private fun showContentState(state: DetailsViewModelState.Content) {
		showSlider(state.details.images)
	}

	private fun showSlider(images: List<String>) {
		val sliderAdapter = SliderAdapter(images)

		with(binding.slider) {
			autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
			setSliderAdapter(sliderAdapter)
			scrollTimeInSec = 3
			isAutoCycle = true
			startAutoCycle()
		}
	}
}