package ru.akimovmaksim.recipesapp.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module
import ru.akimovmaksim.details_screen.data.api.DetailsService
import ru.akimovmaksim.details_screen.ui.navigation.DetailsRouter
import ru.akimovmaksim.image_screen.ui.navigation.ImageRouter
import ru.akimovmaksim.main_screen.data.api.RecipesService
import ru.akimovmaksim.main_screen.ui.navigation.RecipesRouter
import ru.akimovmaksim.recipesapp.cicerone.createCicerone
import ru.akimovmaksim.recipesapp.cicerone.routers.DetailsRouterImpl
import ru.akimovmaksim.recipesapp.cicerone.routers.ImageRouterImpl
import ru.akimovmaksim.recipesapp.cicerone.routers.RecipesRouterImpl
import ru.akimovmaksim.recipesapp.network.createRetrofitService
import ru.akimovmaksim.recipesapp.network.provideGson
import ru.akimovmaksim.recipesapp.network.provideOkHttpClient
import ru.akimovmaksim.recipesapp.network.provideRetrofit

val appModule = module {

single { provideGson() }
	single { provideOkHttpClient() }
	single { provideRetrofit(gson = get(), okHttpClient = get()) }

	single { createCicerone() }
	single { get<Cicerone<Router>>().router }
	single { get<Cicerone<Router>>().getNavigatorHolder() }

	factory<RecipesService> { createRetrofitService(retrofit = get()) }
	factory<DetailsService> { createRetrofitService(retrofit = get()) }

	factory<RecipesRouter> { RecipesRouterImpl(router = get()) }
	factory<DetailsRouter> { DetailsRouterImpl(router = get()) }
	factory<ImageRouter> { ImageRouterImpl(router = get()) }
}