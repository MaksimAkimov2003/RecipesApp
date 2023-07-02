package ru.akimovmaksim.image_screen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.akimovmaksim.image_screen.presentation.ImageViewModel

val imageModule = module {
	viewModel {
		ImageViewModel(router = get())
	}
}