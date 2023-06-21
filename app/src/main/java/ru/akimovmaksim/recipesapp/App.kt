package ru.akimovmaksim.recipesapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.akimovmaksim.main_screen.di.mainScreenModule
import ru.akimovmaksim.recipesapp.di.appModule

class App : Application() {

	override fun onCreate() {
		super.onCreate()

		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@App)
			modules(
				appModule,
				mainScreenModule
			)
		}
	}
}