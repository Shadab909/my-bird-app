package presentation.screen.birdlistscreen


import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import network.data.BirdImageData
import network.repository.BirdImageRepository


class BirdListViewModel(private val birdImageData: BirdImageData) : ViewModel() {

    val uiState = BirdImageRepository.uiState

    init {
        viewModelScope.launch {
            BirdImageRepository.fetchAllImages(
                birdImageData = birdImageData,
                onSuccess = {}
            )
        }
    }

    fun fetchImagesByCategory(category: String) {
        viewModelScope.launch {
            BirdImageRepository.getImagesByCategory(category)
        }
    }

    fun updateSelectedCategory(category: String) {
        viewModelScope.launch {
            BirdImageRepository.updateSelectedCategory(category)
        }
    }


    fun searchImageByAuthor(searchText: String) {
        viewModelScope.launch {
            BirdImageRepository.searchImagesByAuthor(searchText)
        }
    }
}