package ru.akimovmaksim.details_screen.data.model

import com.google.gson.annotations.SerializedName

data class SimilarModel(
	@SerializedName("uuid")
	val id: String,

	@SerializedName("name")
	val name: String,

	@SerializedName("image")
	val poster: String
)
