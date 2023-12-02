package network.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import model.BirdImage

class BirdImageData {
    private val httpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    suspend fun fetchAllImages(
        onSuccess : (List<BirdImage>) -> Unit,
        onError : (String) -> Unit
    ) {
        try {
            val images = httpClient.get("https://sebi.io/demo-image-api/pictures.json").body<List<BirdImage>>()
            onSuccess(images)
        }catch (e: Exception){
            onError(e.message ?: "Unknown error")
        }
    }
}