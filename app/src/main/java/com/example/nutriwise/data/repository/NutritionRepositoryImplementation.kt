import com.example.nutriwise.data.api.CalorieApiService
import com.example.nutriwise.data.repository.ApiRepository
import com.example.nutriwise.domain.model.NutritionData

class NutritionRepositoryImplementation(private val calorieApiService: CalorieApiService) :
    ApiRepository {

    override suspend fun fetchMyNutritionData(query: String): NutritionData {
        val apiKey = getApiKey()
        val response = calorieApiService.fetchNutritionData(apiKey, query)

        // Fetch the first item from the response (or handle empty results appropriately)
        return response.items.firstOrNull()
            ?: throw IllegalStateException("No nutrition data found for the query: $query")
    }

    private fun getApiKey(): String {
        return "sJmja/d7C3WKcdTEF1UAFA==tuYqBbguPSiVIrE7"
    }
}
