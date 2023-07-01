package ru.akimovmaksim.recipesapp.cicerone.routers

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimovmaksim.details_screen.ui.DetailsFragment
import ru.akimovmaksim.main_screen.ui.navigation.RecipesRouter

class RecipesRouterImpl(
	private val router: Router
) : RecipesRouter {

	override fun navigateToDetailsScreen(id: String) {
		router.navigateTo(FragmentScreen { DetailsFragment.newInstance(id) })
	}
}