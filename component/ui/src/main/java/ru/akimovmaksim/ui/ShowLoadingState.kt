package ru.akimovmaksim.ui

import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isVisible

fun showLoadingState(
	content: View,
	progressBar: ProgressBar
) {
	content.isVisible = false
	progressBar.isVisible = true
}

fun hideLoadingState(
	content: View,
	progressBar: ProgressBar
) {
	content.isVisible = true
	progressBar.isVisible = false
}

