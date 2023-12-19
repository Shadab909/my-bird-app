package presentation.bird_list_screen

import domain.model.Bird
import model.BirdImage

data class BirdImageUiState(
    var searchText : String = "",
    var selectedCategory : String = "ALL",
    var images : List<BirdImage> = emptyList()
){
    var categories = images.map { it.category }.toSet()
    var selectedImage = if(searchText.isEmpty()) {
        if (selectedCategory == "ALL") {
            images
        } else {
            images.filter { it.category == selectedCategory }
        }
    }else{
            if (selectedCategory == "ALL"){
                images.filter { it.author.contains(searchText, ignoreCase = true) }
            }else{
                images.filter { it.author.contains(searchText, ignoreCase = true) && it.category == selectedCategory }
            }
        }
}