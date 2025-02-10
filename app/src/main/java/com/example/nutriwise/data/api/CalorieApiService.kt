package com.example.nutriwise.data.api
import com.example.nutriwise.domain.model.NutritionData
import com.example.nutriwise.domain.model.NutritionResponse

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CalorieApiService {
    @GET("/v1/nutrition")
    suspend fun fetchNutritionData(
        @Header("X-Api-Key") apiKey: String,
        @Query("query") query: String
    ): NutritionResponse
}
