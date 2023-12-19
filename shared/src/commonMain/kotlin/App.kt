import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.bird_list_screen.BirdListScreen
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