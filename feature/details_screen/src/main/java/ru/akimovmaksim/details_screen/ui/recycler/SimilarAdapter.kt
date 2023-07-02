package ru.akimovmaksim.details_screen.ui.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.akimovmaksim.details_screen.domain.entity.SimilarEntity

class SimilarAdapter(
	private val onItemCLick: (id: String) -> Unit
) : ListAdapter<SimilarEntity, SimilarViewHolder>(SimilarDiffUtil) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarViewHolder =
		SimilarViewHolder.newInstance(parent)

	override fun onBindViewHolder(holder: SimilarViewHolder, position: Int) {
		holder.bind(getItem(position), onItemCLick)
	}
}