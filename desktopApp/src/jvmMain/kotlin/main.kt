import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "My Bird App" , icon = painterResource("bird-image-icon.jpg")) {
        val interactionSource = remember { MutableInteractionSource() }
        Box (
            modifier = Modifier.hoverable(interactionSource)
        ){
            MainView()
        }
    }
}