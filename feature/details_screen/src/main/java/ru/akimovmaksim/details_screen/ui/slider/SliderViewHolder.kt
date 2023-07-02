package ru.akimovmaksim.details_screen.ui.slider

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import ru.akimovmaksim.details_screen.databinding.SliderItemBinding

class SliderViewHolder(
	private val binding: SliderItemBinding
) : SliderViewAdapter.ViewHolder(binding.root) {

	companion object {

		fun newInstance(parent: ViewGroup): SliderViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = SliderItemBinding.inflate(inflater, parent, false)
			return SliderViewHolder(binding)
		}
	}

	fun bind(
		images: List<String>,
		position: Int,
		onClick: (imageUrl: String) -> Unit
	) {
		with(binding) {
			Glide.with(root.context)
				.load(images[position])
				.into(image)
			image.setOnClickListener { onClick.invoke(images[position]) }
		}
	}
}