package presentation.bird_list_screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getPlatformName
import presentation.bird_list_screen.BirdListState
import util.MColors
import util.MSizes
import presentation.bird_list_screen.BirdListViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CategoryButtons(
    viewModel: BirdListViewModel,
    state: BirdListState
) {

    val keyboard = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 0.dp, bottom = 4.dp)
    ) {
        Button(
            onClick = {
                keyboard?.hide()
                viewModel.searchBirdsByTypeAndSearchText(
                    type = "ALL",
                    searchText = state.searchText
                )
            },
            modifier = Modifier.weight(1f).fillMaxWidth()
                .height(MSizes.getButtonHeight(getPlatformName())),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (state.selectedBirdType == "ALL") {
                    MColors.selectedButtonColor
                } else {
                    MColors.buttonColor
                }
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(
                text = "ALL",
                fontSize = MSizes.getTextSize(getPlatformName()).sp,
                color = Color.White
            )
        }

        state.types.forEach { category ->
            Button(
                onClick = {
                    keyboard?.hide()
                    viewModel.searchBirdsByTypeAndSearchText(
                        type = category,
                        searchText = state.searchText
                    )
                },
                modifier = Modifier.weight(1f).fillMaxWidth()
                    .height(MSizes.getButtonHeight(getPlatformName())),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (state.selectedBirdType == category) {
                        MColors.selectedButtonColor
                    } else {
                        MColors.buttonColor
                    }
                ),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Text(
                    text = category,
                    fontSize = MSizes.getTextSize(getPlatformName()).sp,
                    color = Color.White
                )
            }
        }
    }
}