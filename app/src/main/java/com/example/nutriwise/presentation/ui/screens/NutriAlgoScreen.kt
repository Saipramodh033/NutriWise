package com.example.nutriwise.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.pm.ShortcutInfoCompat.Surface

@Composable
fun NutriAlgoScreen(){
    Surface (
        modifier = Modifier.fillMaxWidth()
    ){
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "here it will be show how nutrition grade is calculated"
            )
        }

    }
}

@Composable
@Preview
fun NutriAlgoScreenPreview(){
    NutriAlgoScreen()
}