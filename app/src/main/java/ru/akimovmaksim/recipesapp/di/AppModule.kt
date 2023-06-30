package ru.akimovmaksim.recipesapp.di

import org.koin.dsl.module
import ru.akimovmaksim.details_screen.data.api.DetailsService
import ru.akimovmaksim.main_screen.data.api.RecipesService
import ru.akimovmaksim.recipesapp.network.createRetrofitService
import ru.akimovmaksim.recipesapp.network.provideGson
import ru.akimovmaksim.recipesapp.network.provideOkHttpClient
import ru.akimovmaksim.recipesapp.network.provideRetrofit

internal val appModule = module {
	single { provideGson() }
	single { provideOkHttpClient() }
	single { provideRetrofit(gson = get(), okHttpClient = get()) }
	factory<RecipesService> { createRetrofitService(retrofit = get()) }
	factory<DetailsService> { createRetrofitService(retrofit = get()) }
}