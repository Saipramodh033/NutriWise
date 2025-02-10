package com.example.nutriwise.di

import NutritionRepositoryImplementation
import com.example.nutriwise.data.repository.ApiRepository
import com.example.nutriwise.domain.usecase.NutritionUseCase
import com.example.nutriwise.presentation.viewModel.NutritionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    includes(networkModule)
    single<ApiRepository> { NutritionRepositoryImplementation(get()) }
    single{NutritionUseCase(get())}
    viewModel { NutritionViewModel(get()) }
}


