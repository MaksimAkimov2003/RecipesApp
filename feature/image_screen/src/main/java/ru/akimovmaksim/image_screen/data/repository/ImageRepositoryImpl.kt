package ru.akimovmaksim.image_screen.data.repository

import android.app.DownloadManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.akimovmaksim.image_screen.domain.repository.ImageRepository

class ImageRepositoryImpl(
	private val downloadManager: DownloadManager?
) : ImageRepository {

	private companion object {

		const val MIME_TYPE = "image/jpg"
		const val FILE_NAME = "recipe.jpg"
		const val DESCRIPTION = "Downloading"
	}

	override suspend fun downloadImage(url: String) {
		val request = DownloadManager.Request(Uri.parse(url))
			.setMimeType(MIME_TYPE)
			.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
			.setTitle(FILE_NAME)
			.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, FILE_NAME)
			.setDescription(DESCRIPTION)
			.setAllowedOverMetered(true)
			.setAllowedOverRoaming(true)

		withContext(Dispatchers.IO) {
			downloadManager?.enqueue(request)
		}
	}

	override suspend fun convertToBitmap(url: String): Bitmap {
		return withContext(Dispatchers.IO) {
			val byteArray = url.toByteArray()
			BitmapFactory
				.decodeByteArray(byteArray, 0, byteArray.size)
		}
	}
}