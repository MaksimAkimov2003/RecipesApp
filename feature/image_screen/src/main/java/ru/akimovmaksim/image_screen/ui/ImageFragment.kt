package ru.akimovmaksim.image_screen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import ru.akimovmaksim.image_screen.R
import ru.akimovmaksim.image_screen.databinding.ImageFragmentBinding
import ru.akimovmaksim.image_screen.presentation.ImageViewModel

class ImageFragment : Fragment(R.layout.image_fragment) {

	private lateinit var binding: ImageFragmentBinding

	private val viewModel by inject<ImageViewModel>()

	companion object {

		private const val IMAGE_FRAGMENT_KEY = "IMAGE_FRAGMENT_KEY"

		fun newInstance(imageUrl: String): ImageFragment {
			return ImageFragment().apply {
				val bundle = Bundle()
				bundle.putString(IMAGE_FRAGMENT_KEY, imageUrl)
				arguments = bundle
			}
		}
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = ImageFragmentBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setImage()
		setListeners()
	}

	private fun setImage() {
		arguments?.getString(IMAGE_FRAGMENT_KEY)?.let {
			Glide.with(requireContext())
				.load(it)
				.into(binding.image)
		}
	}

	private fun setListeners() {
		with(binding) {
			backButton.setOnClickListener { viewModel.navigateBack() }
//			downloadButton.setOnClickListener {  }
		}
	}
}