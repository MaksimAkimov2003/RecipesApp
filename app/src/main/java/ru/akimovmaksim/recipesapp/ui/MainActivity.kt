package ru.akimovmaksim.recipesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import org.koin.android.ext.android.inject
import ru.akimovmaksim.main_screen.ui.RecipesFragment
import ru.akimovmaksim.recipesapp.R

class MainActivity : AppCompatActivity() {

	private val navigatorHolder by inject<NavigatorHolder>()
	private val navigator = AppNavigator(this, R.id.container)
	private val router by inject<Router>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		router.newRootScreen(FragmentScreen { RecipesFragment() })
	}

	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}
}