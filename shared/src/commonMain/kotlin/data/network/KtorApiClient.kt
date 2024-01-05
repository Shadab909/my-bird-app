package data.network

import data.network.dto.BirdDto
import data.network.dto.toBird
import domain.model.Bird
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import util.Resource

class KtorApiClient {

    private val httpClient by lazy {
        HttpClient {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    suspend fun getBirdList(): Resource<List<Bird>> {
        return try {
            val birdDto =
                httpClient.get("https://sebi.io/demo-image-api/pictures.json").body<List<BirdDto>>()
            val birds = birdDto.map { it.toBird() }
            Resource.Success(birds)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }
}