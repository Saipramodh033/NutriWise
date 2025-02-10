package com.example.nutriwise.domain.model

data class NutritionResult(
    val nutritionGrade: String,
    val gradeIndication: String,
    val nutritionList: List<NutritionalValues>,
    val color: androidx.compose.ui.graphics.Color
)
