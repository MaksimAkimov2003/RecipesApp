package ru.akimovmaksim.image_screen.presentation

sealed class ImageViewModelState {
	object Initial : ImageViewModelState()
	object Loading : ImageViewModelState()
	object Complete : ImageViewModelState()
	object ConnectionError : ImageViewModelState()
}
