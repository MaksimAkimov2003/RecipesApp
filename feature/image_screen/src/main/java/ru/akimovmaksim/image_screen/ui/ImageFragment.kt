package ru.akimovmaksim.image_screen.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.koin.android.ext.android.inject
import ru.akimovmaksim.image_screen.R
import ru.akimovmaksim.image_screen.databinding.ImageFragmentBinding
import ru.akimovmaksim.image_screen.presentation.ImageViewModel
import ru.akimovmaksim.image_screen.presentation.ImageViewModelState
import ru.akimovmaksim.ui.showConnectionErrorState
import ru.akimovmaksim.ui.showLoadingState

class ImageFragment : Fragment(R.layout.image_fragment) {

	private lateinit var binding: ImageFragmentBinding
	private var url: String? = null

	private val viewModel by inject<ImageViewModel>()

	companion object {

		private const val IMAGE_FRAGMENT_KEY = "IMAGE_FRAGMENT_KEY"
		private const val EXTERNAL_STORAGE_PERMISSION_CODE = 100

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
		getArgument()
		setImage()
		setListeners()
		setObserves()
	}

	private fun downLoadImage() {
		url?.let { it1 ->
			viewModel.downloadImage(
				it1,
				requireContext().contentResolver,
				"${System.currentTimeMillis()}"
			)
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)

		if (requestCode != EXTERNAL_STORAGE_PERMISSION_CODE) return

		for (result in grantResults) {
			if (result != PackageManager.PERMISSION_GRANTED) return
		}

		downLoadImage()
	}

	private fun getArgument() {
		arguments?.getString(IMAGE_FRAGMENT_KEY)?.let {
			url = it
		}
	}

	private fun checkPermissions() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			downLoadImage()
		} else {
			requestPermissions(
				arrayOf(
					Manifest.permission.WRITE_EXTERNAL_STORAGE,
					Manifest.permission.READ_EXTERNAL_STORAGE
				),
				EXTERNAL_STORAGE_PERMISSION_CODE
			)
		}
	}

	private fun setObserves() {
		viewModel.state.observe(viewLifecycleOwner, ::handleState)
	}

	private fun handleState(state: ImageViewModelState) {
		when (state) {
			is ImageViewModelState.Initial         -> Unit
			is ImageViewModelState.Loading         -> showLoadingState(
				content = binding.content,
				progressBar = binding.progressBar
			)
			is ImageViewModelState.Complete        -> showCompleteState()
			is ImageViewModelState.ConnectionError -> showConnectionErrorState(
				content = binding.content,
				progressBar = binding.progressBar,
				context = context,
				message = getString(ru.akimovmaksim.resources.R.string.error_message)
			)
		}
	}

	private fun showCompleteState() {
		with(binding) {
			content.isVisible = true
			progressBar.isVisible = false
		}
		Toast.makeText(
			context, getString(ru.akimovmaksim.resources.R.string.complete_message),
			Toast.LENGTH_LONG
		).show()
	}

	private fun setImage() {
		Glide.with(requireContext())
			.load(url)
			.into(binding.image)
	}

	private fun setListeners() {
		with(binding) {
			backButton.setOnClickListener { viewModel.navigateBack() }
			downloadButton.setOnClickListener {
				checkPermissions()
			}
		}
	}
}