package presentation.screen.birddetailscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import util.MSizes.EXPANDED_TOP_BAR_HEIGHT

@Composable
fun ExpandedTopBar(selectedImage : String , category : String, navigator : Navigator) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.primaryVariant)
            .fillMaxWidth()
            .height(EXPANDED_TOP_BAR_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        KamelImage(
            asyncPainterResource(selectedImage),
            contentDescription = "Compose Multiplatform icon",
            modifier = Modifier.fillMaxWidth().aspectRatio(1f),
            contentScale = ContentScale.FillBounds,
            onLoading = {
                Text(text = "Loading image...")
            },
            onFailure = {
                Text(text = "Failed to load image")
            },
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = category,
            color = Color.White,
            style = MaterialTheme.typography.h3,
        )
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .height(30.dp)
                .width(30.dp)
                .clickable {
                    navigator.pop()
                }.align(Alignment.TopStart),
            tint = Color.White
        )
    }
}