package presentation.bird_list_screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import domain.model.Bird
import getPlatformName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import presentation.bird_details_screen.BirdDetailsScreen
import util.MSizes

@Composable
fun ImageDialog(
    openDialog : MutableState<Boolean>,
    selectedImage: MutableState<String>,
    selectedBird: MutableState<Bird>,
    navigator : Navigator
) {
        Card(
            modifier = Modifier.clickable {
                openDialog.value = false
                selectedImage.value = ""
            },
            elevation = 16.dp,
        ){
                Box{
                    KamelImage(
                        asyncPainterResource(selectedImage.value),
                        contentDescription = "Compose Multiplatform icon",
                        modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                        contentScale = ContentScale.FillBounds,
                        onLoading = {
                            Text("Loading image...")
                        },
                        onFailure = {
                            Text("Failed to load image")
                        },
                    )

                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier
                            .padding(4.dp)
                            .height(30.dp)
                            .width(30.dp)
                            .align(Alignment.TopStart)
                            .clickable {
                                openDialog.value = false
                            },
                        tint = Color.White
                    )

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = "Details",
                        modifier = Modifier
                            .padding(4.dp)
                            .height(30.dp)
                            .width(30.dp)
                            .align(Alignment.TopEnd)
                            .clickable {
                                openDialog.value = false
                                navigator.push(BirdDetailsScreen(selectedBird.value))
                            },
                        tint = Color.White
                    )

                    Column (
                        horizontalAlignment = Alignment.Start ,
                        modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).background(
                            color=Color.Black.copy(alpha = 0.65f))
                    ){
                        Text(
                            text = "Category: ${selectedBird.value.type}",
                            modifier = Modifier.padding(start=8.dp,end=8.dp,top=8.dp),
                            fontSize = MSizes.getTextSize(getPlatformName()).sp,
                            color = Color.White
                        )
                        Text(
                            text = "Author: ${selectedBird.value.photographer}",
                            modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp,bottom = 8.dp),
                            fontSize = MSizes.getTextSize(getPlatformName()).sp,
                            color = Color.White
                        )
                }
            }

        }
}