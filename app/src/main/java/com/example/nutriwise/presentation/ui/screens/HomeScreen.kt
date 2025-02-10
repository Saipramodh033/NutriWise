package com.example.nutriwise.presentation.ui.screens

import NutriScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.NutriWiseTheme
import com.example.nutriwise.R
import com.example.nutriwise.domain.model.NutritionResult
import com.example.nutriwise.presentation.viewModel.NutritionViewModel
import com.example.nutriwise.presentation.viewModel.uiState


@Composable
fun HomeScreen(nutritionUiState: uiState,modifier: Modifier){
    when(nutritionUiState){
        is uiState.Error -> ErrorScreen()
        is uiState.Loading -> LoadingScreen()
        is uiState.Success-> NutritionGradeScreen(nutritionUiState.nutritionResult)
        is uiState.HomeScreen -> InformationScreen()
    }
}
@Composable
fun InformationScreen( modifier: Modifier = Modifier) {
    NutriWiseTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = Color(0xFFF5FCF5)
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NutriCard(
                    learn = "Become\nnutri-wise",
                    onClick = { /* Handle click action here */ }, modifier =
                        Modifier.padding(top = 60.dp)
                )
                IntroCard(modifier = Modifier, intro = R.string.intro)
                NutriAlgo(
                    "nutri algo",
                    modifier = Modifier
                        .width(200.dp)
                        .height(80.dp),
                    onClick = { /* Handle click action here */ }
                )
                IntroCard(modifier = Modifier.height(160.dp), intro = R.string.intro2)
            }
        }
    }
}

@Composable
private fun IntroCard(modifier: Modifier, intro: Int) {
    Card(
        modifier = modifier
            .padding(12.dp)
            ,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor =Color(0xFFDAEED7))
    ) {
        Text(
            text = stringResource(intro),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,

            color = Color.Black
        )
    }
}

@Composable

fun NutriCard(
    learn: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .height(160.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,

            pressedElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.nutri1),
                            colorResource(id = R.color.nutri2)
                        )
                    )
                )
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = learn,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.Yellow,
                        fontFamily = FontFamily(Font(R.font.baloo_tammudu)),
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

            }
        }
    }
}
@Composable

fun NutriAlgo(
    learn: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(18.dp)
            .height(120.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            colorResource(id = R.color.nutri1),
                            colorResource(id = R.color.nutri2)
                        )
                    )
                )
                .fillMaxSize()
                .padding(6.dp)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {

                Text(
                    text = learn,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.Yellow,
                        fontFamily = FontFamily(Font(R.font.baloo_tammudu)),
                        fontSize = 19.sp
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun NutritionGradeScreen(nutritionResult: NutritionResult){
    val grade = nutritionResult.nutritionGrade
    val indicator = nutritionResult.gradeIndication
    val gradeColor = nutritionResult.color
    val nutrients = nutritionResult.nutritionList
    NutriScreen(grade,indicator,gradeColor,nutrients)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ErrorScreen(){
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "error",
                fontSize = 36.sp

            )
        }
    }
}
@Composable
fun LoadingScreen(){
    Surface (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "loading...",
                fontSize = 36.sp

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NutriWiseTheme {
//        HomeScreen("Become\nNUTRI\nWise")
    }
}


