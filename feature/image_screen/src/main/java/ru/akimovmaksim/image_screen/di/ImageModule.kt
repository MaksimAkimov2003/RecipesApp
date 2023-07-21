package ru.akimovmaksim.image_screen.di

import androidx.core.content.getSystemService
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.akimovmaksim.image_screen.data.repository.ImageRepositoryImpl
import ru.akimovmaksim.image_screen.domain.repository.ImageRepository
import ru.akimovmaksim.image_screen.domain.use_case.ConvertToBitmapUseCase
import ru.akimovmaksim.image_screen.domain.use_case.DownloadImageUseCase
import ru.akimovmaksim.image_screen.presentation.ImageViewModel

val imageModule = module {

	factory<ImageRepository> {
		ImageRepositoryImpl(
			downloadManager = androidContext().getSystemService()
		)
	}
	factory { ConvertToBitmapUseCase(repository = get()) }
	factory { DownloadImageUseCase(repository = get()) }

	viewModel {
		ImageViewModel(
			router = get(),
			convertToBitmapUseCase = get(),
			downloadImageUseCase = get()
		)
	}
}