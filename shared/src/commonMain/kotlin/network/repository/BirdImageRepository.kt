package network.repository


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import presentation.bird_list_screen.BirdImageUiState
import network.data.BirdImageData

class BirdImageRepository {

    companion object {
        private val _uiState = MutableStateFlow(BirdImageUiState())
        val uiState = _uiState.asStateFlow()

        fun getImagesByCategory(category: String) {
            CoroutineScope(Dispatchers.IO).launch {
                _uiState.update { state ->
                    state.copy(
                        selectedCategory = category,
                    )
                }
            }
        }

        fun fetchAllImages(
            birdImageData: BirdImageData,
            onSuccess : (Boolean) -> Unit
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                birdImageData.fetchAllImages(
                    onSuccess = {
                        _uiState.update {state->
                            state.copy(
                                images = it,
                                selectedCategory = "ALL",
                                searchText = ""
                            )
                        }
                        onSuccess(true)
                    },
                    onError = {
                        println(it)
                    }
                )
            }
        }

        fun searchImagesByAuthor(author: String){
            if (author.isEmpty()) {
                _uiState.update { state ->
                    state.copy(
                        searchText = author
                    )
                }
            } else {
                CoroutineScope(Dispatchers.IO).launch {
                    _uiState.update { state ->
                        state.copy(
                            searchText = author
                        )
                    }
                }
            }
        }

        fun updateSelectedCategory(category: String) {
            _uiState.update {
                it.copy(selectedCategory = category)
            }
        }

    }


}