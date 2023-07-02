package ru.akimovmaksim.image_screen.presentation

import androidx.lifecycle.ViewModel
import ru.akimovmaksim.image_screen.ui.navigation.ImageRouter

class ImageViewModel(
	private val router: ImageRouter
) : ViewModel() {

	fun navigateBack() {
		router.exit()
	}
}