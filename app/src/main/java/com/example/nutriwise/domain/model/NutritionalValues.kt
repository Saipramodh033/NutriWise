package com.example.nutriwise.domain.model

import androidx.compose.ui.graphics.Color

data class NutritionalValues(
    val name: String,
    val percentage: Double,
    val color: Color,
    val icon: String,
    val excessCaution:String,
    val upper: Double,
    val lower: Double,
    val lowerCaution:String,
    val pointColor: Color,
)
