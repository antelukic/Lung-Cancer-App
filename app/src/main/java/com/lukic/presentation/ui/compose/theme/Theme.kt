package com.lukic.presentation.ui.compose.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = blueGrey800,
    primaryContainer = camel,
    secondary = gray300,
    secondaryContainer = gray50,
    background = deepOrange200Light
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = AppTypography,
        content = content
    )
}
