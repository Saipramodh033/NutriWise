package com.example.nutriwise.di

import com.example.nutriwise.data.api.provideCalorieApiService
import com.example.nutriwise.data.api.provideMoshi
import com.example.nutriwise.data.api.provideOkHttpClient
import com.example.nutriwise.data.api.provideRetrofit
import org.koin.dsl.module

val networkModule = module{
    single { provideMoshi() }
    single { provideOkHttpClient() }
    single { provideRetrofit() }
    single { provideCalorieApiService() }
}


