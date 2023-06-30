package ru.akimovmaksim.details_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.akimovmaksim.details_screen.R
import ru.akimovmaksim.details_screen.databinding.DetailsFragmentBinding
import ru.akimovmaksim.details_screen.presentation.DetailsViewModel
import ru.akimovmaksim.details_screen.presentation.DetailsViewModelState

class DetailsFragment : Fragment(R.layout.details_fragment) {

	private lateinit var binding: DetailsFragmentBinding

	private val viewModel by inject<DetailsViewModel>()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = DetailsFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	private fun handleState(state: DetailsViewModelState) {
		when (state) {
			is DetailsViewModelState.Initial -> Unit
			is DetailsViewModelState.Loading -> Unit
			is DetailsViewModelState.Content -> Unit
		}
	}
}