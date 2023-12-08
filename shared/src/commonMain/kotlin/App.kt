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
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import components.CategoryButtons
import components.ImageDialog
import components.TopSearchBar
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import model.BirdImage
import network.data.BirdImageData
import screen.BirdListScreen
import util.Sizes.Companion.getImageSize
import viewmodel.BirdImageViewModel


@Composable
fun App() {
    MaterialTheme(
        shapes = Shapes(small = RoundedCornerShape(0.dp) , medium = RoundedCornerShape(0.dp) , large = RoundedCornerShape(0.dp)),
        colors = Colors(
            primary = Color.Black.copy(alpha = 0.85f),
            primaryVariant = Color.Black,
            secondary = Color.Black.copy(alpha = 0.85f),
            secondaryVariant = Color.Black,
            background  = Color(0xFF666362),
            surface =  Color.White,
            error =  Color.Red,
            onPrimary =  Color.White,
            onSecondary = Color.White,
            onBackground =  Color.White,
            onSurface =  Color.White,
            onError =  Color.White,
            isLight =  false
        )
    ) {
        if (getPlatformName() == "Desktop") {
            Navigator(BirdListScreen())
        } else {
            Navigator(BirdListScreen()) { navigator ->
                SlideTransition(navigator = navigator)
            }
        }
    }


}


expect fun getPlatformName(): String