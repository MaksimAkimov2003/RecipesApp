package ru.akimovmaksim.main_screen.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

class RecipesAdapter : ListAdapter<RecipeEntity, RecipesViewHolder>(RecipesDiffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder =
		RecipesViewHolder.newInstance(parent)

	override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
		holder.bind(getItem(position))
	}
}