package ru.akimovmaksim.ui

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible

fun Context.showConnectionErrorState(
	content: View,
	progressBar: ProgressBar,
	message: String
) {
	content.isVisible = true
	progressBar.isVisible = false
	Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}