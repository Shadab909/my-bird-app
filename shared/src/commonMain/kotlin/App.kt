import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.network.KtorApiClient
import data.repository.BirdListRepositoryImpl
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import domain.use_case.GetBirdsUseCase
import presentation.bird_list_screen.BirdListScreen
import presentation.bird_list_screen.BirdListViewModel
import util.MColors
import util.MShapes


@Composable
fun App() {
    MaterialTheme(
        colors = MColors.colors,
        shapes = MShapes.shapes
    ) {

        val ktorApiClient = KtorApiClient()
        val birdListRepository = BirdListRepositoryImpl(ktorApiClient)
        val viewModel = getViewModel(
            BirdListViewModel::class,
            viewModelFactory { BirdListViewModel(GetBirdsUseCase(birdListRepository)) })

        Navigator(BirdListScreen(viewModel)){
            SlideTransition(
                navigator = it,
            )
        }
    }
}


expect fun getPlatformName(): String