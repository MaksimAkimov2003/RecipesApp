package ru.akimovmaksim.image_screen.domain.repository

import android.graphics.Bitmap

interface ImageRepository {

	suspend fun downloadImage(url: String)
	suspend fun convertToBitmap(url: String): Bitmap
}