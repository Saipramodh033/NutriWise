package com.example.nutriwise.data.repository

import com.example.nutriwise.domain.model.NutritionData

interface ApiRepository {
    suspend fun fetchMyNutritionData (query: String):NutritionData
}