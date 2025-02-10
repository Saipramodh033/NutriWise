import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.nutriwise.R
import com.example.nutriwise.domain.model.NutritionalValues

@Composable
fun NutriScreen(
    nutritionGrade: String,
    nutritionIndicator: String,
    gradeColor: Color,
    nutrients: List<NutritionalValues>
){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFDAEED7)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            NutriGradeCard(nutritionGrade,nutritionIndicator,gradeColor,nutrients)
            NutritionProgressCard(nutrients = nutrients, totalKcal = "457")
        }
    }


}

@Composable
fun NutritionProgressCard(nutrients: List<NutritionalValues>, totalKcal: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // Header
            Text(
                text = "Nutrition values",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$totalKcal Kcal",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Dynamic nutrient rows
            nutrients.forEach { nutrient ->
                NutrientRow(
                    name = nutrient.name,
                    percentage = nutrient.percentage,
                    color = nutrient.color,
                    icon = nutrient.icon
                )
            }
        }
    }
}

@Composable
fun CautionText(caution:String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
        )
    {
        Text(

            text = "- $caution",
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = Color.DarkGray
        )
    }
}

@Composable
fun NutrientRow(name: String, percentage: Double, color: Color, icon: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Nutrient Icon
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(color = color.copy(alpha = 0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = icon, fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Nutrient Name and Progress Bar
        Column(modifier = Modifier.weight(1f)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = name, color = Color.Black, fontSize = 14.sp)
                Text(text = "$percentage%", color = Color.Gray, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { (percentage / 100f).toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .background(color = Color.LightGray, shape = RoundedCornerShape(12.dp)),
                color = color,
            )
        }
    }
}

@Composable
fun NutriGradeCard(
    nutritionGrade: String,
    nutritionIndicator: String,
    gradeColor: Color,
    nutrients: List<NutritionalValues>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
        color = Color(0xFFDAEED7)

    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 10.dp, end = 10.dp, bottom = 10.dp),
            shape = RoundedCornerShape(topStart = 48.dp, topEnd = 48.dp, bottomStart = 8.dp, bottomEnd = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        )
        {
            Column (modifier = Modifier.fillMaxWidth()){
                Box(modifier = Modifier) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nutrition Grade",
                            modifier = Modifier.padding(start = 20.dp, top = 16.dp, bottom = 10.dp),
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp),
                            colors = CardDefaults.cardColors(containerColor = gradeColor)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = nutritionIndicator,
                                    modifier = Modifier.padding(start = 20.dp, top = 12.dp, bottom = 10.dp),
                                    fontSize = 28.sp,
                                    color = Color.Black
                                )
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(13.dp)

                        ) {
                            // Nutri-grade bars
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(end = 1.dp)
                                    .background(Color(0xFF28672E)) // Grade A
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(end = 1.dp)
                                    .background(Color(0xFF4CAF50)) // Grade B
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(end = 1.dp)
                                    .background(Color(0xFFFFEB3B)) // Grade C
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .padding(end = 1.dp)
                                    .background(colorResource(id = R.color.orange)) // Grade D
                            )
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(Color(0xFFF44336)) // Grade E
                            )
                        }
                    }

                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .height(90.dp)
                            .width(90.dp)
                            .padding(start = 16.dp, top = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),

                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = nutritionGrade,
                                modifier = Modifier.padding(top = 12.dp, bottom = 10.dp),
                                fontSize = 48.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RectangleShape
                ) {
                    Column (modifier = Modifier.fillMaxWidth()){
                        nutrients.forEach { nutrient ->
                            if(nutrient.percentage>nutrient.upper){
                                CautionText(nutrient.excessCaution)
                            }
                            if(nutrient.percentage<nutrient.lower){
                                CautionText(nutrient.lowerCaution)
                            }
                        }
                    }

                }


            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNutritionProgressCard() {
//    NutriScreen()
}
