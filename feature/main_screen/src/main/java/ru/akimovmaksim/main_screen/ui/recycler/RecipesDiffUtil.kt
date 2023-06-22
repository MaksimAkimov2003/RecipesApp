package ru.akimovmaksim.main_screen.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity

object RecipesDiffUtil : DiffUtil.ItemCallback<RecipeEntity>() {

	override fun areItemsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean =
		oldItem == newItem

	override fun areContentsTheSame(oldItem: RecipeEntity, newItem: RecipeEntity): Boolean =
		oldItem.id == newItem.id
}