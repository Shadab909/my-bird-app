package presentation.screen.birddetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.screen.birddetailscreen.components.CollapsedTopBar
import presentation.screen.birddetailscreen.components.ExpandedTopBar
import getPlatformName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdImage
import util.MColors
import util.MString

class BirdDetailsScreen(private val bird: BirdImage) : Screen {
    @Composable
    override fun Content() {
        val selectedImage = "https://sebi.io/demo-image-api/${bird.path}"
        val navigator = LocalNavigator.currentOrThrow
        Surface(modifier = Modifier.fillMaxSize().background(color = MColors.backgroundColor)) {
            if (getPlatformName() == "Desktop") {
                Row(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(
                                weight = 1f,
                                fill = true
                            )
                    ) {
                        KamelImage(
                            asyncPainterResource(selectedImage),
                            contentDescription = "Compose Multiplatform icon",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds,
                            onLoading = {
                                Text(text = "Loading image...")
                            },
                            onFailure = {
                                Text(text = "Failed to load image")
                            },
                        )
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .padding(16.dp)
                                .height(30.dp)
                                .width(30.dp)
                                .align(Alignment.TopStart)
                                .clickable {
                                    navigator.pop()
                                },
                            tint = Color.White
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(
                                weight = 1f,
                                fill = true
                            )
                    ) {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Text(
                                    text = "author : ${bird.author}",
                                    fontSize = 24.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxSize()
                                        .background(color = MColors.backgroundColor),
                                    color = Color.White
                                )
                            }

                            item {
                                Text(
                                    text = "category : ${bird.category}",
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxSize()
                                        .background(color = MColors.backgroundColor),
                                    color = Color.White
                                )
                            }

                            item {
                                Spacer(modifier = Modifier.fillMaxWidth().height(16.dp))
                                Text(
                                    text = MString.tempText,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxSize()
                                        .background(color = MColors.backgroundColor).padding(16.dp),
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            } else {
                val listState = rememberLazyListState()
                val isCollapsed: Boolean by remember {
                    derivedStateOf {
                        listState.firstVisibleItemIndex > 0
                    }
                }
                Box {
                    CollapsedTopBar(navigator = navigator, category = bird.category, modifier = Modifier.zIndex(2f), isCollapsed = isCollapsed)
                    LazyColumn(state = listState) {
                        item { ExpandedTopBar(selectedImage, bird.category , navigator) }
                        item {
                            Text(
                                text = MString.tempText,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxSize()
                                    .background(color = MColors.backgroundColor).padding(16.dp),
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}