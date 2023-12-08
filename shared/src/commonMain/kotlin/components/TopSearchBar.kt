package components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.updateLiveLiteralValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import model.BirdImageUiState
import viewmodel.BirdImageViewModel

@Composable
fun TopSearchBar(
    viewModel: BirdImageViewModel,
    state: BirdImageUiState
) {

    var searchText by remember { mutableStateOf(state.searchText) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colors.background)
            .padding(start = 6.dp , end = 6.dp )
    ){
        OutlinedTextField(
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFEEEEEE),
                unfocusedBorderColor = Color(0xFFEEEEEE),
                backgroundColor = Color(0xFFEEEEEE),
                focusedLabelColor = Color.Black,
                trailingIconColor = Color.Gray,
                leadingIconColor = Color.Gray,
                placeholderColor = Color.Gray,
                textColor = Color.Black,
            ),
            value = searchText,
            onValueChange = {
                searchText = it
                viewModel.updateSearchText(it)
            },
            label = {
                Text(text = "Search Image by Author", color = Color.Gray)
            },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(4.dp),
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
                            .width(24.dp).clickable {
                                searchText = ""
                                viewModel.updateSearchText("")
                            }
                    )
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Done
            )
        )
    }
}