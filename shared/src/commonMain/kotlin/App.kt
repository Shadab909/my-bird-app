import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import components.CategoryButtons
import components.ImageDialog
import components.TopSearchBar
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdImage
import network.data.BirdImageData
import util.MColors
import util.Sizes.Companion.getImageSize
import viewmodel.BirdImageViewModel




@Composable
fun App() {


    MaterialTheme(
        colors = Colors(
            primary = MColors.primaryColor,
            primaryVariant = MColors.primaryVariantColor,
            secondary = MColors.secondaryColor,
            secondaryVariant = MColors.secondaryVariantColor,
            background = MColors.backgroundColor,
            surface = MColors.surfaceColor,
            error = MColors.errorColor,
            onPrimary = MColors.onPrimaryColor,
            onSecondary = MColors.onSecondaryColor,
            onBackground = MColors.onBackgroundColor,
            onSurface = MColors.onSurfaceColor,
            onError = MColors.onErrorColor,
            isLight = false
        ),
        shapes = Shapes(
            small = RoundedCornerShape(0.dp),
            medium = RoundedCornerShape(0.dp),
            large = RoundedCornerShape(0.dp),
        )
    ) {

        val birdImageData = BirdImageData()

        val viewModel = getViewModel(
            BirdImageViewModel::class,
            viewModelFactory { BirdImageViewModel(birdImageData) })

        val state by viewModel.uiState.collectAsState()

        val openDialog = remember { mutableStateOf(false) }

        val selectedImage = remember { mutableStateOf("") }

        val selectedBird = remember {
            mutableStateOf(
                BirdImage(
                    author = "",
                    category = "",
                    path = ""
                )
            )
        }

        LaunchedEffect(state.selectedCategory) {
            viewModel.fetchImagesByCategory(state.selectedCategory)
        }


        Box(modifier = Modifier.fillMaxSize()) {

            AnimatedVisibility(
                visible = state.images.isEmpty(),
                modifier = Modifier.fillMaxSize().align(Alignment.Center).background(color = MColors.backgroundColor),
            ) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Loading.....",
                        fontSize = 20.sp,
                        color = Color.White,
                        textAlign = Center
                    )
                }
            }

            AnimatedVisibility(
                visible = state.images.isNotEmpty(),
                label = "Loading.....",
                modifier = Modifier.fillMaxSize().align(Alignment.Center),
                enter = slideIn (
                    animationSpec  = spring(
                    stiffness = Spring.StiffnessMediumLow,
                    visibilityThreshold = IntOffset.VisibilityThreshold
                    ),
                    initialOffset = { IntOffset(0, 0) }
            )
            ) {
                Column(
                    Modifier.fillMaxSize().background(color = MaterialTheme.colors.onPrimary),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    TopSearchBar(
                        viewModel = viewModel,
                        state = state
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).padding(8.dp))

                    CategoryButtons(
                        viewModel = viewModel,
                        state = state
                    )

                    Spacer(modifier = Modifier.fillMaxWidth().height(2.dp).padding(8.dp))

                    AnimatedVisibility(
                        visible = state.selectedImage.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
                    ) {

                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(getImageSize(getPlatformName())),
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            items(state.selectedImage.size) { index ->
                                KamelImage(
                                    asyncPainterResource("https://sebi.io/demo-image-api/${state.selectedImage[index].path}"),
                                    contentDescription = "Compose Multiplatform icon",
                                    modifier = Modifier.weight(1f).aspectRatio(1f).clickable {
                                        selectedImage.value =
                                            "https://sebi.io/demo-image-api/${state.selectedImage[index].path}"
                                        selectedBird.value = state.selectedImage[index]
                                        openDialog.value = true
                                    },
                                    contentScale = ContentScale.Crop,
                                    onLoading = {
                                        Text(text="Loading image...", color = Color.White)
                                    },
                                    onFailure = {
                                        Text(text="Failed to load image",color=Color.White)
                                    },
                                )
                            }
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = openDialog.value,
                modifier = if (getPlatformName() == "Desktop") {
                    Modifier.fillMaxHeight(0.6f).aspectRatio(1f).align(Alignment.Center)
                } else {
                    Modifier.fillMaxWidth(0.6f).aspectRatio(1f).align(Alignment.Center)
                },
            ) {
                ImageDialog(
                    openDialog = openDialog,
                    selectedImage = selectedImage,
                    selectedBird = selectedBird
                )
            }
        }
    }
}


expect fun getPlatformName(): String