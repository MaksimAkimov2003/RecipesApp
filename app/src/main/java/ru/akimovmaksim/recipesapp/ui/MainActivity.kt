package ru.akimovmaksim.recipesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.akimovmaksim.main_screen.ui.RecipesFragment
import ru.akimovmaksim.recipesapp.R

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val fragment = RecipesFragment()

		supportFragmentManager.beginTransaction()
			.replace(R.id.container, fragment)
			.commit()
	}

}