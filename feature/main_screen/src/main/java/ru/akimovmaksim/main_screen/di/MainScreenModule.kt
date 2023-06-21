package ru.akimovmaksim.main_screen.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.akimovmaksim.main_screen.data.data_source.RecipesDataSource
import ru.akimovmaksim.main_screen.data.data_source.RecipesDataSourceImpl
import ru.akimovmaksim.main_screen.data.repository.RecipesRepositoryImpl
import ru.akimovmaksim.main_screen.domain.repository.RecipesRepository
import ru.akimovmaksim.main_screen.domain.use_case.GetRecipesUseCase
import ru.akimovmaksim.main_screen.presentation.RecipesViewModel

val mainScreenModule = module {
	factory<RecipesDataSource> { RecipesDataSourceImpl(api = get()) }
	factory<RecipesRepository> { RecipesRepositoryImpl(dataSource = get()) }
	factory { GetRecipesUseCase(repository = get()) }
	viewModel {
		RecipesViewModel(getRecipesUseCase = get())
	}
}