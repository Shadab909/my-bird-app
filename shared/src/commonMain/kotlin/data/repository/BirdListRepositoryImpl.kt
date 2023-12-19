package data.repository

import data.network.KtorApiClient
import data.network.dto.BirdDto
import data.network.dto.toBird
import domain.model.Bird
import domain.repository.BirdListRepository
import util.Resource

class BirdListRepositoryImpl(
    private val ktorApiClient: KtorApiClient
) : BirdListRepository {
    override suspend fun getBirdList(): Resource<List<Bird>> {
        return ktorApiClient.getBirdList()
    }
}