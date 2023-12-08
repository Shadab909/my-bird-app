package screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import getPlatformName
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdImage
import util.Sizes

class BirdDetails(private val birdImage: BirdImage) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                TopAppBar(backgroundColor = Color.Black.copy(alpha = 0.85f), elevation = 0.dp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Close",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            navigator.pop()
                        }.padding(8.dp)
                    )

                    Text(
                        text = "Bird Details",
                        modifier = Modifier.padding(8.dp),
                        color = Color.White,
                    )
                }
            }
        ) {
            if (getPlatformName() == "Desktop") {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    KamelImage(
                        asyncPainterResource("https://sebi.io/demo-image-api/${birdImage.path}"),
                        contentDescription = "Compose Multiplatform icon",
                        modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                        contentScale = ContentScale.FillBounds,
                        onLoading = {
                            Text("Loading image...")
                        },
                        onFailure = {
                            Text("Failed to load image")
                        },
                    )
                    Column(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Category : ${birdImage.category}",
                            fontSize = Sizes.getTextSize(getPlatformName()).sp
                        )
                        Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                        Text(
                            text = "Author : ${birdImage.author}",
                            fontSize = Sizes.getTextSize(getPlatformName()).sp
                        )
                    }
                }
            }else{
                Column(modifier = Modifier.fillMaxSize()){
                    KamelImage(
                        asyncPainterResource("https://sebi.io/demo-image-api/${birdImage.path}"),
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
                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                    Text(
                        text = "Category : ${birdImage.category}",
                        fontSize = Sizes.getTextSize(getPlatformName()).sp
                    )
                    Spacer(modifier = Modifier.fillMaxWidth().height(1.dp))
                    Text(
                        text = "Author : ${birdImage.author}",
                        fontSize = Sizes.getTextSize(getPlatformName()).sp
                    )
                }
            }
        }
    }
}