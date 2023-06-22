package ru.akimovmaksim.recipesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.akimovmaksim.main_screen.ui.RecipesFragment
import ru.akimovmaksim.recipesapp.R

class MainActivity : AppCompatActivity() {

	private lateinit var fragment: Fragment

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		fragment = RecipesFragment()

		supportFragmentManager.beginTransaction()
			.add(R.id.container, fragment)
			.commit()
	}

}