package com.example.nutriwise.presentation.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutriwise.domain.model.NutritionResult
import com.example.nutriwise.domain.usecase.NutritionUseCase
import kotlinx.coroutines.launch

sealed interface uiState {
    data class Success(val nutritionResult: NutritionResult) : uiState
    object Loading : uiState
    data class Error(val message: String = "An error occurred") : uiState
    object HomeScreen : uiState
}

class NutritionViewModel(private val nutritionUseCase: NutritionUseCase) : ViewModel() {

    var nutritionState: uiState by mutableStateOf(uiState.HomeScreen)
        private set

    var searchActive by mutableStateOf(false)
        private set

    var productName by mutableStateOf("")
        private set

    fun onSearchClick() {
        searchActive = true
    }

    fun onCloseClick() {
        searchActive = false
        productName = ""
        nutritionState = uiState.HomeScreen
    }

    fun onQueryChange(query: String) {
        productName = query.trim()
        if (productName.isNotBlank()) {
            getResult(productName)
        } else {
            nutritionState = uiState.HomeScreen
        }
    }

    private fun getResult(query: String) {
        nutritionState = uiState.Loading
        Log.d("viewmodel", "Fetching nutrition data for query: $query")
        viewModelScope.launch {
            try {
                val result = nutritionUseCase.calculateNutrition(query)
                nutritionState = uiState.Success(result)
            } catch (e: Exception) {
                e.printStackTrace()
                nutritionState = uiState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
