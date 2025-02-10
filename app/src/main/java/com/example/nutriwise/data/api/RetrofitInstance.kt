package com.example.nutriwise.data.api

import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


fun provideMoshi(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory()) // Support for Kotlin data classes
        .build()
}

fun provideOkHttpClient(): OkHttpClient{
    return OkHttpClient.Builder().build()
}

fun provideRetrofit(): Retrofit {
    Log.d("DI", "Retrofit instance created")
    return Retrofit.Builder()
        .baseUrl("https://api.calorieninjas.com/")
        .addConverterFactory(MoshiConverterFactory.create(provideMoshi()))
        .client(provideOkHttpClient())
        .build()
}


fun provideCalorieApiService(): CalorieApiService {
    val retrofit = provideRetrofit()
    return retrofit.create(CalorieApiService::class.java)
}


