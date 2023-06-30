package ru.akimovmaksim.details_screen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.akimovmaksim.details_screen.data.data_source.DetailsDataSource
import ru.akimovmaksim.details_screen.data.data_source.DetailsDataSourceImpl
import ru.akimovmaksim.details_screen.data.repository.DetailsRepositoryImpl
import ru.akimovmaksim.details_screen.domain.repository.DetailsRepository
import ru.akimovmaksim.details_screen.domain.use_case.GetDetailsUseCase
import ru.akimovmaksim.details_screen.presentation.DetailsViewModel

val detailsModule = module {
	factory<DetailsDataSource> { DetailsDataSourceImpl(api = get()) }
	factory<DetailsRepository> { DetailsRepositoryImpl(dataSource = get()) }
	factory { GetDetailsUseCase(repository = get()) }
	viewModel {
		DetailsViewModel(
			getDetailsUseCase = get()
		)
	}
}