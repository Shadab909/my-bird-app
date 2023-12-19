package util


import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object MSizes {
    private val IMAGE_SMALL = 120.dp
    private val IMAGE_MEDIUM = 240.dp

    private const val TEXT_SMALL = 12
    private const val TEXT_LARGE = 20

    private val BUTTON_HEIGHT_SMALL = 40.dp
    private val BUTTON_HEIGHT_LARGE = 60.dp

    val COLLAPSED_TOP_BAR_HEIGHT = 56.dp
    val EXPANDED_TOP_BAR_HEIGHT = 250.dp

    fun getImageSize(platformName: String): Dp {
        return when (platformName) {
            "Desktop" -> IMAGE_MEDIUM
            else -> IMAGE_SMALL
        }
    }

    fun getTextSize(platformName: String): Int {
        return when (platformName) {
            "Desktop" -> TEXT_LARGE
            else -> TEXT_SMALL
        }
    }

    fun getButtonHeight(platformName: String): Dp {
        return when (platformName) {
            "Desktop" -> BUTTON_HEIGHT_LARGE
            else -> BUTTON_HEIGHT_SMALL
        }
    }
}
