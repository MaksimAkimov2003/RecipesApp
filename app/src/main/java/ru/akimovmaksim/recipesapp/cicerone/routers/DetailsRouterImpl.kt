package ru.akimovmaksim.recipesapp.cicerone.routers

import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.akimovmaksim.details_screen.ui.DetailsFragment
import ru.akimovmaksim.details_screen.ui.navigation.DetailsRouter
import ru.akimovmaksim.image_screen.ui.ImageFragment

class DetailsRouterImpl(
	private val router: Router
) : DetailsRouter {

	override fun refreshScreen(id: String) {
		router.replaceScreen(FragmentScreen { DetailsFragment.newInstance(id) })
	}

	override fun exit() {
		router.exit()
	}

	override fun navigateToImageScreen(url: String) {
		router.navigateTo(FragmentScreen { ImageFragment.newInstance(url) })
	}
}