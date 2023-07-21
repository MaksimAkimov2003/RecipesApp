package ru.akimovmaksim.image_screen.domain.use_case

import android.graphics.Bitmap
import ru.akimovmaksim.image_screen.domain.repository.ImageRepository

class ConvertToBitmapUseCase(
	private val repository: ImageRepository
) {

	suspend fun invoke(imageUrl: String): Result<Bitmap> {
		return runCatching {
			repository.convertToBitmap(imageUrl)
		}
	}
}