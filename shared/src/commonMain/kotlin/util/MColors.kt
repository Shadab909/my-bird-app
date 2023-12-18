package util

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

object MColors {
    private const val primary = 0xFF757575
    private const val primaryVariant = 0xFF757575
    private const val secondary = 0xFF757575
    private const val secondaryVariant = 0xFF757575
    private const val background = 0xFF676362
    private const val surface = 0xFF676362
    private const val error = 0xFFB00020
    private const val onPrimary = 0xFF676362
    private const val onSecondary = 0xFF676362
    private const val onBackground = 0xFF676362
    private const val onSurface = 0xFF676362
    private const val onError = 0xFFFFFFFF
    private const val button = 0xFF888888
    private const val selectedButton = 0xFF0f0f0f

    //convert above into Color objects
    val primaryColor = Color(primary)
    private val primaryVariantColor = Color(primaryVariant)
    private val secondaryColor = Color(secondary)
    private val secondaryVariantColor = Color(secondaryVariant)
    val backgroundColor = Color(background)
    private val surfaceColor = Color(surface)
    private val errorColor = Color(error)
    private val onPrimaryColor = Color(onPrimary)
    private val onSecondaryColor = Color(onSecondary)
    private val onBackgroundColor = Color(onBackground)
    private val onSurfaceColor = Color(onSurface)
    private val onErrorColor = Color(onError)
    val buttonColor = Color(button)
    val selectedButtonColor = Color(selectedButton)

    val colors = Colors(
        primary = primaryColor,
        primaryVariant = primaryVariantColor,
        secondary = secondaryColor,
        secondaryVariant = secondaryVariantColor,
        background = backgroundColor,
        surface = surfaceColor,
        error = errorColor,
        onPrimary = onPrimaryColor,
        onSecondary = onSecondaryColor,
        onBackground = onBackgroundColor,
        onSurface = onSurfaceColor,
        onError = onErrorColor,
        isLight = false
    )

}