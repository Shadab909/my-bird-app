package viewmodel


import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.launch
import network.data.BirdImageData
import network.repository.BirdImageRepository


class BirdImageViewModel(private val birdImageData: BirdImageData) : ViewModel() {

    val uiState = BirdImageRepository.uiState

    init {
        viewModelScope.launch {
            BirdImageRepository.fetchAllImages(
                birdImageData = birdImageData,
                onSuccess = {}
            )
        }
    }

    fun fetchImagesByCategory(category: String,searchText: String) {
        viewModelScope.launch {
            BirdImageRepository.getImagesByCategorySearch(category,searchText)
        }
    }

    fun updateSelectedCategory(category: String) {
        viewModelScope.launch {
            BirdImageRepository.updateSelectedCategory(category)
        }
    }

    fun updateSearchText(searchText: String) {
        viewModelScope.launch {
            BirdImageRepository.updateSearchText(searchText)
        }
    }


    fun searchImageByAuthor(searchText: String) {
        viewModelScope.launch {
            BirdImageRepository.searchImagesByAuthor(searchText)
        }
    }
}