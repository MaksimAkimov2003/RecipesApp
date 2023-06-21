package ru.akimovmaksim.recipesapp.network

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.akimovmaksim.recipesapp.BuildConfig

internal fun provideGson(): Gson = Gson()

internal fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
	.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
	.build()

internal fun provideRetrofit(
	gson: Gson,
	okHttpClient: OkHttpClient
): Retrofit = Retrofit.Builder()
	.baseUrl(BuildConfig.BASE_URL)
	.client(okHttpClient)
	.addConverterFactory(GsonConverterFactory.create(gson))
	.build()