package domain.use_case

import domain.model.Bird
import domain.repository.BirdListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import util.Resource

class GetBirdsUseCase (
    private val repository: BirdListRepository
) {
    operator fun invoke() : Flow<Resource<List<Bird>>> = flow {
        emit(Resource.Loading())
        emit(repository.getBirdList())
    }
}