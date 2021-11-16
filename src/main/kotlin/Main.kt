import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    val icon = painterResource("icon.webp")
    Window(
        onCloseRequest = ::exitApplication,
        icon = icon,
    ) {
        app()
    }

}
