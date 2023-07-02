package ru.akimovmaksim.recipesapp.cicerone.routers

import com.github.terrakok.cicerone.Router
import ru.akimovmaksim.image_screen.ui.navigation.ImageRouter

class ImageRouterImpl(
	private val router: Router
) : ImageRouter {

	override fun exit() {
		router.exit()
	}
}