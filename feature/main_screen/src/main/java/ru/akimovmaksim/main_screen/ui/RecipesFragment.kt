package ru.akimovmaksim.main_screen.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import ru.akimovmaksim.main_screen.R
import ru.akimovmaksim.main_screen.presentation.RecipesViewModel

class RecipesFragment : Fragment(R.layout.recipes_fragment) {

	private val viewModel by inject<RecipesViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.getRecipes()
	}
}