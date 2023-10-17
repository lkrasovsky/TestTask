package com.example.testtask.presentation.theme

import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

sealed class ThemeColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color
) {
    object Night : ThemeColors(
        primary = Purple80,
        secondary = PurpleGrey80,
        tertiary = Pink80,
    )

    object Day : ThemeColors(
        primary = Purple40,
        secondary = PurpleGrey40,
        tertiary = Pink40,
    )
}