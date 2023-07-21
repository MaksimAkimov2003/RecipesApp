package ru.akimovmaksim.image_screen.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.akimovmaksim.image_screen.domain.use_case.ConvertToBitmapUseCase
import ru.akimovmaksim.image_screen.domain.use_case.DownloadImageUseCase
import ru.akimovmaksim.image_screen.ui.navigation.ImageRouter

class ImageViewModel(
	private val router: ImageRouter,
	private val convertToBitmapUseCase: ConvertToBitmapUseCase,
	private val downloadImageUseCase: DownloadImageUseCase
) : ViewModel() {

	private companion object {

		const val QUALITY = 100
	}

	private val _state = MutableLiveData<ImageViewModelState>(ImageViewModelState.Initial)
	val state: LiveData<ImageViewModelState> = _state

	fun downloadImage(url: String) {
		viewModelScope.launch {
			val result = downloadImageUseCase.invoke(url)

			result.onSuccess {
				_state.value = ImageViewModelState.Complete
			}

			result.onFailure {
				_state.value = ImageViewModelState.ConnectionError
			}
		}
	}

//	fun downloadImage(
//		imageUrl: String,
//		contentResolver: ContentResolver,
//		name: String
//	) {
//		_state.value = ImageViewModelState.Loading
//		viewModelScope.launch(Dispatchers.IO) {
//			val bitmap = convertToBitmap(imageUrl)
//			bitmap?.let { saveToStorage(it, contentResolver, name) }
//			_state.postValue(ImageViewModelState.Complete)
//		}
//	}
//
//	private suspend fun convertToBitmap(imageUrl: String): Bitmap? {
//		val url = URL(imageUrl)
//
//		try {
//			val connection = withContext(Dispatchers.IO) {
//				url.openConnection()
//			} as HttpURLConnection
//			val inputStream = connection.inputStream
//			val bufferedInputStream = BufferedInputStream(inputStream)
//
//			return BitmapFactory.decodeStream(bufferedInputStream)
//
//		} catch (e: Throwable) {
//			_state.value = ImageViewModelState.ConnectionError
//		}
//
//		return null
//	}
//
//	private suspend fun saveToStorage(
//		bitmap: Bitmap,
//		contentResolver: ContentResolver,
//		name: String
//	) {
//		val fileName = "${name}.jpg"
//		var fos: OutputStream?
//
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//			contentResolver.let { resolver ->
//				val contentValues = ContentValues().apply {
//					put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
//					put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
//					put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
//				}
//				val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
//				fos = imageUri?.let { resolver.openOutputStream(it) }
//			}
//		} else {
//			val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
//			val image = File(imagesDir, fileName)
//			fos = withContext(Dispatchers.IO) {
//				FileOutputStream(image)
//			}
//		}
//		fos?.use {
//			bitmap.compress(Bitmap.CompressFormat.JPEG, QUALITY, it)
//		}
//	}

	fun navigateBack() {
		router.exit()
	}
}