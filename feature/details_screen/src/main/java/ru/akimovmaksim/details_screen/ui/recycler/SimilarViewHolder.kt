package ru.akimovmaksim.details_screen.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.akimovmaksim.details_screen.domain.entity.SimilarEntity
import ru.akimovmaksim.resources.databinding.RecipeItemBinding

class SimilarViewHolder(
	private val binding: RecipeItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

	companion object {

		fun newInstance(parent: ViewGroup): SimilarViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = RecipeItemBinding.inflate(inflater, parent, false)
			return SimilarViewHolder(binding)
		}
	}

	fun bind(recipe: SimilarEntity, onClick: (id: String) -> Unit) {
		binding.run {
			Glide.with(root.context)
				.load(recipe.poster)
				.into(recipeImage)
			header.text = recipe.name
			recipeItem.setOnClickListener { onClick.invoke(recipe.id) }
		}
	}

}