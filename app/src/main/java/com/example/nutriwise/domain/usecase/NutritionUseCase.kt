package com.example.nutriwise.domain.usecase

import androidx.compose.ui.graphics.Color
import com.example.nutriwise.data.repository.ApiRepository
import com.example.nutriwise.domain.model.NutritionData
import com.example.nutriwise.domain.model.NutritionResult
import com.example.nutriwise.domain.model.NutritionalValues

class NutritionUseCase(private val repository: ApiRepository) {

    suspend fun calculateNutrition(query: String): NutritionResult {
        val nutritionData = repository.fetchMyNutritionData(query)

        val (nutritionGrade, gradeIndicator) = calculateNutritionGrade(nutritionData)
        val nutritionList = fetchNutritionList(nutritionData)
        val indicatorColor = when (nutritionGrade) {
            "A" -> Color(0xFF28672E) // Dark Green
            "B" -> Color(0xFF4CAF50) // Light Green
            "C" -> Color(0xFFFFEB3B) // Yellow
            "D" -> Color(0xFFFF9800) // Orange
            "E" -> Color(0xFFF44336) // Red
            else -> Color(0xFFFF5151) // Gray (default color for undefined grades)
        }

        return NutritionResult(nutritionGrade, gradeIndicator, nutritionList, indicatorColor)
    }

    private fun calculateNutritionGrade(nutritionData: NutritionData): Pair<String, String> {
        // Calculate negative points based on international dietary guidelines
        val energyPoints = when {
            nutritionData.calories < 335 -> 0
            nutritionData.calories in 335.0..670.0 -> 1
            nutritionData.calories > 670 && nutritionData.calories <= 1005 -> 2
            nutritionData.calories > 1005 && nutritionData.calories <= 1340 -> 3
            nutritionData.calories > 1340 && nutritionData.calories <= 2000 -> 5
            nutritionData.calories > 2000 -> 10
            else -> 0
        }

        // Sugar Points Calculation
        val sugarPoints = when {
            nutritionData.sugarG < 4.5 -> 0
            nutritionData.sugarG in 4.5..9.0 -> 1
            nutritionData.sugarG > 9 && nutritionData.sugarG <= 13.5 -> 2
            nutritionData.sugarG > 13.5 && nutritionData.sugarG <= 18 -> 3
            nutritionData.sugarG > 18 && nutritionData.sugarG <= 50 -> 5
            nutritionData.sugarG > 50 -> 10
            else -> 0
        }

        // Saturated Fat Points Calculation
        val saturatedFatPoints = when {
            nutritionData.fatSaturatedG < 1 -> 0
            nutritionData.fatSaturatedG in 1.0..2.0 -> 1
            nutritionData.fatSaturatedG > 2 && nutritionData.fatSaturatedG <= 3 -> 2
            nutritionData.fatSaturatedG > 3 && nutritionData.fatSaturatedG <= 4 -> 3
            nutritionData.fatSaturatedG > 4 && nutritionData.fatSaturatedG <= 10 -> 5
            nutritionData.fatSaturatedG > 10 -> 10
            else -> 0
        }

        // Sodium Points Calculation
        val sodiumPoints = when {
            nutritionData.sodiumMg < 100 -> 0
            nutritionData.sodiumMg in 100.0..200.0 -> 1
            nutritionData.sodiumMg > 200 && nutritionData.sodiumMg <= 300 -> 2
            nutritionData.sodiumMg > 300 && nutritionData.sodiumMg <= 400 -> 3
            nutritionData.sodiumMg > 400 && nutritionData.sodiumMg <= 800 -> 5
            nutritionData.sodiumMg > 800 -> 10
            else -> 0
        }

        // Fiber Points Calculation
        val fiberPoints = when {
            nutritionData.fiberG < 0.9 -> 0
            nutritionData.fiberG in 0.9..1.9 -> 1
            nutritionData.fiberG > 1.9 && nutritionData.fiberG <= 2.8 -> 2
            nutritionData.fiberG > 2.8 && nutritionData.fiberG <= 3.5 -> 3
            nutritionData.fiberG > 3.5 && nutritionData.fiberG <= 5 -> 4
            nutritionData.fiberG > 5 -> 5
            else -> 0
        }

        // Protein Points Calculation
        val proteinPoints = when {
            nutritionData.proteinG < 1.6 -> 0
            nutritionData.proteinG in 1.6..3.2 -> 1
            nutritionData.proteinG > 3.2 && nutritionData.proteinG <= 4.8 -> 2
            nutritionData.proteinG > 4.8 && nutritionData.proteinG <= 6.4 -> 3
            nutritionData.proteinG > 6.4 && nutritionData.proteinG <= 8 -> 4
            nutritionData.proteinG > 8 -> 5
            else -> 0
        }

        // Potassium Points Calculation
        val potassiumPoints = when {
            nutritionData.potassiumMg < 200 -> 0
            nutritionData.potassiumMg in 200.0..400.0 -> 1
            nutritionData.potassiumMg > 400 && nutritionData.potassiumMg <= 600 -> 2
            nutritionData.potassiumMg > 600 && nutritionData.potassiumMg <= 800 -> 3
            nutritionData.potassiumMg > 800 && nutritionData.potassiumMg <= 1500 -> 4
            nutritionData.potassiumMg > 1500 -> 5
            else -> 0
        }

        val negativePoints = energyPoints + sugarPoints + saturatedFatPoints + sodiumPoints
        val positivePoints = fiberPoints + proteinPoints + potassiumPoints
        // Final score
        val netScore = negativePoints - positivePoints

        val result = when {
            netScore <= -1 -> "A" to "Excellent"
            netScore in 0..2 -> "B" to "Good"
            netScore in 3..10 -> "C" to "Moderate"
            netScore in 11..18 -> "D" to "Poor"
            netScore > 18 -> "E" to "Very Poor"
            else -> "Unknown" to "Unknown"
        }

        return result
    }

    private fun fetchNutritionList(nutritionData: NutritionData): List<NutritionalValues> {
        return listOf(
            NutritionalValues(
                name = "Proteins",
                percentage = nutritionData.proteinG,
                color = Color(0xFF4CAF50),
                icon = "\uD83C\uDF31",
                excessCaution = "This has a good amount of proteins.",
                upper = 8.0,
                lower = 0.0,
                lowerCaution = "No proteins.",
                pointColor = Color.Green
            ),
            NutritionalValues(
                name = "Carbohydrates",
                percentage = nutritionData.carbohydratesTotalG,
                color = Color(0xFFFFC107),
                icon = "\uD83C\uDF6C",
                excessCaution = "Excess carbohydrates can lead to weight gain.",
                upper = 50.0,
                lower = 20.0,
                lowerCaution = "Too little carbohydrates may lead to low energy.",
                pointColor = Color.Yellow
            ),
            NutritionalValues(
                name = "Fats",
                percentage = nutritionData.fatTotalG,
                color = Color(0xFFF44336),
                icon = "\uD83C\uDF6D",
                excessCaution = "Excessive fats can lead to health problems.",
                upper = 30.0,
                lower = 5.0,
                lowerCaution = "Too little fat might affect body functions.",
                pointColor = Color.Red
            ),
            NutritionalValues(
                name = "Fiber",
                percentage = nutritionData.fiberG,
                color = Color(0xFF9C27B0),
                icon = "\uD83C\uDF6F",
                excessCaution = "Fiber is great for digestion.",
                upper = 25.0,
                lower = 5.0,
                lowerCaution = "Too little fiber may cause digestive issues.",
                pointColor = Color.Magenta
            ),
            NutritionalValues(
                name = "Sugar",
                percentage = nutritionData.sugarG,
                color = Color(0xFF03A9F4),
                icon = "\uD83C\uDF6E",
                excessCaution = "Excess sugar can lead to health risks like diabetes.",
                upper = 35.0,
                lower = 0.0,
                lowerCaution = "Very low sugar can affect energy levels.",
                pointColor = Color.Cyan
            ),
            NutritionalValues(
                name = "Sodium",
                percentage = nutritionData.sodiumMg,
                color = Color(0xFF2196F3),
                icon = "\uD83C\uDF8A",
                excessCaution = "Excess sodium can increase the risk of high blood pressure.",
                upper = 2300.0,
                lower = 0.0,
                lowerCaution = "Too low sodium may affect electrolyte balance.",
                pointColor = Color.Blue
            ),
            NutritionalValues(
                name = "Potassium",
                percentage = nutritionData.potassiumMg,
                color = Color(0xFF8BC34A),
                icon = "\uD83C\uDF4E",
                excessCaution = "Adequate potassium supports healthy muscle function.",
                upper = 4700.0,
                lower = 1000.0,
                lowerCaution = "Low potassium can affect muscle and nerve functions.",
                pointColor = Color.Green
            ),
            NutritionalValues(
                name = "Cholesterol",
                percentage = nutritionData.cholesterolMg,
                color = Color(0xFFFF5722),
                icon = "\uD83C\uDFB6",
                excessCaution = "High cholesterol can lead to heart disease.",
                upper = 300.0,
                lower = 0.0,
                lowerCaution = "No cholesterol is unusual and may indicate a health issue.",
                pointColor = Color.Yellow
            )
        )
    }
}
