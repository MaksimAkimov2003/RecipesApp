package ru.akimovmaksim.recipesapp.network

import retrofit2.Retrofit

internal inline fun <reified T> createRetrofitService(retrofit: Retrofit): T =
	retrofit.create(T::class.java)