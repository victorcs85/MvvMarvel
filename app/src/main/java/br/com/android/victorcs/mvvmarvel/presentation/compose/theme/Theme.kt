package br.com.android.victorcs.mvvmarvel.presentation.compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import br.com.android.victorcs.mvvmarvel.R

@Composable
fun mvvmTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val darkColorPalette = darkColors(
        primary = colorResource(id = R.color.color_primary_dark),
        primaryVariant = colorResource(id = R.color.color_accent),
        secondary = colorResource(id = R.color.color_primary)
    )

    val lightColorPalette = lightColors(
        primary = colorResource(id = R.color.color_light_primary),
        primaryVariant = colorResource(id = R.color.color_light_accent),
        secondary = colorResource(id = R.color.color_light_primary)
    )

    val colors = if (darkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}