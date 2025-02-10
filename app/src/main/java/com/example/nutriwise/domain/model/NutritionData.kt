package com.example.nutriwise.domain.model

import com.squareup.moshi.Json

data class NutritionResponse(
    @Json(name = "items") val items: List<NutritionData>
)

data class NutritionData(
    @Json(name = "name") val name: String,
    @Json(name = "serving_size_g") val servingSizeG: Double,
    @Json(name = "calories") val calories: Double,
    @Json(name = "sugar_g") val sugarG: Double,
    @Json(name = "fiber_g") val fiberG: Double,
    @Json(name = "sodium_mg") val sodiumMg: Double,
    @Json(name = "potassium_mg") val potassiumMg: Double,
    @Json(name = "fat_saturated_g") val fatSaturatedG: Double,
    @Json(name = "fat_total_g") val fatTotalG: Double,
    @Json(name = "cholesterol_mg") val cholesterolMg: Double,
    @Json(name = "protein_g") val proteinG: Double,
    @Json(name = "carbohydrates_total_g") val carbohydratesTotalG: Double
)

