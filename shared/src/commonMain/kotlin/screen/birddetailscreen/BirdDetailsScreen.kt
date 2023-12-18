package screen.birddetailscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import model.BirdImage
import util.MColors

class BirdDetailsScreen(private val bird : BirdImage) : Screen {
    @Composable
    override fun Content() {
        Surface(modifier = Modifier.fillMaxSize().background(color = MColors.backgroundColor)) {
            Text(text = bird.author , color = Color.White , modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        }
    }
}