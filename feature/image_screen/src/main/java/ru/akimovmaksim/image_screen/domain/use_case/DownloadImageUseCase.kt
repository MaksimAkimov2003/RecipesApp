package ru.akimovmaksim.image_screen.domain.use_case

import ru.akimovmaksim.image_screen.domain.repository.ImageRepository

class DownloadImageUseCase(
	private val repository: ImageRepository
) {

	suspend fun invoke(imageUrl: String): Result<Unit> {
		return runCatching {
			repository.downloadImage(imageUrl)
		}
	}
}