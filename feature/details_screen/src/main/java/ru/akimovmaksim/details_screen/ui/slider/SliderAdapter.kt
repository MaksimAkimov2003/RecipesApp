package ru.akimovmaksim.details_screen.ui.slider

import android.view.ViewGroup
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(
	private val images: List<String>,
	private val onClick: (imageUrl: String) -> Unit
) : SliderViewAdapter<SliderViewHolder>() {

	override fun getCount(): Int = images.size

	override fun onCreateViewHolder(parent: ViewGroup) = SliderViewHolder.newInstance(parent = parent)

	override fun onBindViewHolder(viewHolder: SliderViewHolder, position: Int) {
		viewHolder.bind(images, position, onClick)
	}
}