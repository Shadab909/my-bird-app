package presentation.screen.birdlistscreen.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import presentation.screen.birdlistscreen.BirdImageUiState
import util.MColors
import presentation.screen.birdlistscreen.BirdListViewModel

@Composable
fun TopSearchBar(
    viewModel: BirdListViewModel,
    state: BirdImageUiState
) {

    var searchText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier.fillMaxWidth().padding(start = 5.dp , end = 5.dp , top = 0.dp),
    ){
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                cursorColor = MColors.primaryColor,
                textColor = Color.Black,
                leadingIconColor = MColors.buttonColor,
                trailingIconColor = MColors.buttonColor,
                backgroundColor = Color.White
            ),
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.searchImageByAuthor(it)
            },
            label = {
                Text(text="Search Image by Author", color = Color.Black)
            },
            modifier = Modifier.weight(1f).fillMaxWidth().padding(4.dp),
            maxLines = 1,
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .padding(4.dp)
                        .height(24.dp)
                        .width(24.dp)
                )
            },
            trailingIcon = {
                if (state.searchText.isNotEmpty()) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Clear Search",
                        modifier = Modifier
                            .padding(4.dp)
                            .height(24.dp)
                            .width(24.dp)
                            .clickable {
                                searchText = ""
                                viewModel.searchImageByAuthor("")
                            }
                            .pointerHoverIcon(
                                PointerIcon.Default
                            )
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            )
        )
    }
}