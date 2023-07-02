package ru.akimovmaksim.details_screen.ui.recycler

import androidx.recyclerview.widget.DiffUtil
import ru.akimovmaksim.details_screen.domain.entity.SimilarEntity

object SimilarDiffUtil : DiffUtil.ItemCallback<SimilarEntity>() {

	override fun areItemsTheSame(oldItem: SimilarEntity, newItem: SimilarEntity): Boolean =
		oldItem == newItem

	override fun areContentsTheSame(oldItem: SimilarEntity, newItem: SimilarEntity): Boolean =
		oldItem.id == newItem.id
}