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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import getPlatformName
import presentation.bird_list_screen.BirdImageUiState
import util.MColors
import util.MSizes
import presentation.bird_list_screen.BirdListViewModel

@Composable
fun CategoryButtons(
    viewModel: BirdListViewModel,
    state: BirdImageUiState
){
    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth().padding(start = 8.dp , end = 8.dp , top = 0.dp , bottom = 4.dp)
    ){

        Button(
            onClick = {
                viewModel.updateSelectedCategory("ALL")
            },
            modifier = Modifier.weight(1f).fillMaxWidth().height(MSizes.getButtonHeight(getPlatformName())),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (state.selectedCategory == "ALL"){
                    MColors.selectedButtonColor
                } else {
                    MColors.buttonColor
                }
            ),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(text="ALL", fontSize = MSizes.getTextSize(getPlatformName()).sp , color = Color.White)
        }


        state.categories.forEach { category ->
            Button(
                onClick = {
                    viewModel.updateSelectedCategory(category)
                },
                modifier = Modifier.weight(1f).fillMaxWidth().height(MSizes.getButtonHeight(getPlatformName())),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (state.selectedCategory == category){
                        MColors.selectedButtonColor
                    } else {
                        MColors.buttonColor
                    }
                ),
                elevation = ButtonDefaults.elevation(0.dp)
            ) {
                Text(text=category, fontSize = MSizes.getTextSize(getPlatformName()).sp , color = Color.White)
            }
        }
    }
}