package ru.akimovmaksim.main_screen.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.akimovmaksim.main_screen.domain.entity.RecipeEntity
import ru.akimovmaksim.resources.databinding.RecipeItemBinding

class RecipesViewHolder(
	private val binding: RecipeItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun newInstance(parent: ViewGroup): RecipesViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = RecipeItemBinding.inflate(inflater, parent, false)
			return RecipesViewHolder(binding)
		}
	}

	fun bind(recipe: RecipeEntity, onClick: (id: String) -> Unit) {
		binding.run {
			Glide.with(root.context)
				.load(recipe.images[0])
				.into(recipeImage)
			header.text = recipe.name
			description.text = recipe.description
			recipeItem.setOnClickListener { onClick.invoke(recipe.id) }
		}
	}

}