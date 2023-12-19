import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import cafe.adriel.voyager.transitions.ScaleTransition
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screen.birdlistscreen.BirdListScreen
import util.MColors
import util.MShapes


@Composable
fun App() {
    MaterialTheme(
        colors = MColors.colors,
        shapes = MShapes.shapes
    ) {
        Navigator(BirdListScreen()){
            SlideTransition(
                navigator = it,
            )
        }
    }
}


expect fun getPlatformName(): String