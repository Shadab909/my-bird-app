package presentation.bird_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.use_case.GetBirdsUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import util.Resource

class NewBirdListViewModel(
    private val getBirdsUseCase: GetBirdsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BirdListState())
    val state: State<BirdListState> = _state

    init {
        getBirds()
    }

    private fun getBirds(){
        getBirdsUseCase().onEach {result ->
            when(result){
                is Resource.Success -> {
                    _state.value = BirdListState(birdList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = BirdListState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = BirdListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getBirdsByType(type: String) {
        viewModelScope.launch {
            _state.value = BirdListState(isLoading = true)
            _state.value = BirdListState(selectedBirdType = type)
        }
    }

    fun updateSelectedBirdType(category: String) {
        viewModelScope.launch {
            _state.value = BirdListState(isLoading = true)
            _state.value = BirdListState(selectedBirdType = category)
        }
    }


    fun searchBirdsByPhotographer(searchText: String) {
        viewModelScope.launch {
            _state.value = BirdListState(isLoading = true)
            _state.value = BirdListState(searchText = searchText)
        }
    }
}