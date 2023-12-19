package domain.repository

import domain.model.Bird
import util.Resource

interface BirdListRepository {
    suspend fun getBirdList(): Resource<List<Bird>>
}