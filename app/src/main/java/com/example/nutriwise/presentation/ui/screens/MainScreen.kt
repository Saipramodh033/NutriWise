package com.example.nutriwise.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nutriwise.R
import com.example.nutriwise.presentation.viewModel.NutritionViewModel
import com.example.nutriwise.presentation.viewModel.uiState
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val nutritionViewModel: NutritionViewModel = getViewModel()
    val nutritionUiState = nutritionViewModel.nutritionState

    Scaffold(
        topBar = {
            TopBar(
                nutritionViewModel = nutritionViewModel,
                nutritionUiState = nutritionUiState
            )
        },
        content = { paddingValues ->
            // Make the content scrollable by wrapping it in a Column with a verticalScroll modifier
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()), // Adding vertical scroll
                verticalArrangement = Arrangement.spacedBy(6.dp),

            ) {
                HomeScreen(
                    nutritionUiState = nutritionUiState,
                    modifier = Modifier.fillMaxWidth()
                )
                // Add more content or spacers as needed to test the scrollable behavior
                Spacer(modifier = Modifier.height(2.dp)) // Example for additional content
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(nutritionViewModel: NutritionViewModel, nutritionUiState: uiState) {
    val searchActive = nutritionViewModel.searchActive
    val productName = nutritionViewModel.productName

    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        title = {
            AnimatedVisibility(
                visible = !searchActive,
                enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.slideInHorizontally(),
                exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.slideOutHorizontally()
            ) {
                Text(
                    text = "Letss..go---->",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.DarkGray,
                        fontFamily = FontFamily(Font(R.font.baloo_tammudu)),
                        fontSize = 26.sp
                    )
                )
            }

            AnimatedVisibility(
                visible = searchActive,
                enter = androidx.compose.animation.fadeIn() + androidx.compose.animation.slideInHorizontally(),
                exit = androidx.compose.animation.fadeOut() + androidx.compose.animation.slideOutHorizontally()
            ) {
                TextField(
                    value = productName,
                    onValueChange = { nutritionViewModel.onQueryChange(it) },
                    placeholder = { Text("Search...") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),

                )
            }
        },
        actions = {
            if (searchActive) {
                IconButton(onClick = { nutritionViewModel.onCloseClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Close search",
                        tint = Color.Red
                    )
                }
            } else {
                IconButton(onClick = { nutritionViewModel.onSearchClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        tint = Color.Green,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }
    )
}



@Preview
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
