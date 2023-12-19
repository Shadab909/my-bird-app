package presentation.bird_list_screen

import domain.model.Bird

data class BirdListState(
    var isLoading : Boolean = false,
    var error : String = "",
    var searchText: String = "",
    var selectedBirdType: String = "ALL",
    var birdList: List<Bird> = emptyList()
) {
    var types = birdList.map { it.type }.toSet()
    var selectedBird = if (searchText.isEmpty()) {
        if (selectedBirdType == "ALL") {
            birdList
        } else {
            birdList.filter { it.type == selectedBirdType }
        }
    } else {
        if (selectedBirdType == "ALL") {
            birdList.filter { it.photographer.contains(searchText, ignoreCase = true) }
        } else {
            birdList.filter {
                it.photographer.contains(
                    searchText,
                    ignoreCase = true
                ) && it.type == selectedBirdType
            }
        }
    }
}