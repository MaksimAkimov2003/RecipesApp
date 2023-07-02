package ru.akimovmaksim.details_screen.ui.navigation

interface DetailsRouter {

	fun refreshScreen(id: String)

	fun exit()

	fun navigateToImageScreen(url: String)
}