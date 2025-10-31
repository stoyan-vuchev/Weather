/*
 * MIT License
 *
 * Copyright (c) 2025 Stoyan Vuchev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.stoyanvuchev.weather.core.ui.theme.color

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.ColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkClearNightColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkCloudyDayColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkCloudyNightColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkFogColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkRainColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkSnowColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkStormColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkSunnyDayColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightClearNightColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightCloudyDayColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightCloudyNightColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightFogColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightRainColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightSnowColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightStormColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightSunnyDayColorPaletteTokens
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import com.stoyanvuchev.weather.domain.other.WeatherConditionCategory
import com.stoyanvuchev.weather.domain.other.category

@Stable
data class ColorPalette(
    val primary: Color,
    val onPrimary: Color,
    val secondary: Color,
    val onSecondary: Color,
    val surfaceLow: Color,
    val onSurfaceLow: Color,
    val surfaceMedium: Color,
    val onSurfaceMedium: Color,
    val surfaceHigh: Color,
    val onSurfaceHigh: Color,
    val error: Color,
    val conditionPrimary: Color,
    val conditionSecondary: Color
)

@Stable
fun colorPalette(
    darkTheme: Boolean,
    darkTokens: ColorPaletteTokens,
    lightTokens: ColorPaletteTokens
): ColorPalette {
    val tokens = if (darkTheme) darkTokens else lightTokens
    return ColorPalette(
        primary = tokens.primary,
        onPrimary = tokens.onPrimary,
        secondary = tokens.secondary,
        onSecondary = tokens.onSecondary,
        surfaceLow = tokens.surfaceLow,
        onSurfaceLow = tokens.onSurfaceLow,
        surfaceMedium = tokens.surfaceMedium,
        onSurfaceMedium = tokens.onSurfaceMedium,
        surfaceHigh = tokens.surfaceHigh,
        onSurfaceHigh = tokens.onSurfaceHigh,
        error = tokens.error,
        conditionPrimary = tokens.conditionPrimary,
        conditionSecondary = tokens.conditionSecondary
    )
}

@Stable
fun WeatherCondition.toColorPalette(darkTheme: Boolean) = when (this.category()) {

    WeatherConditionCategory.SUNNY -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkSunnyDayColorPaletteTokens,
        lightTokens = LightSunnyDayColorPaletteTokens
    )

    WeatherConditionCategory.CLEAR -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkClearNightColorPaletteTokens,
        lightTokens = LightClearNightColorPaletteTokens
    )

    WeatherConditionCategory.CLOUDY_DAY -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkCloudyDayColorPaletteTokens,
        lightTokens = LightCloudyDayColorPaletteTokens
    )

    WeatherConditionCategory.CLOUDY_NIGHT -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkCloudyNightColorPaletteTokens,
        lightTokens = LightCloudyNightColorPaletteTokens
    )

    WeatherConditionCategory.RAIN -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkRainColorPaletteTokens,
        lightTokens = LightRainColorPaletteTokens
    )

    WeatherConditionCategory.STORM -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkStormColorPaletteTokens,
        lightTokens = LightStormColorPaletteTokens
    )

    WeatherConditionCategory.SNOW -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkSnowColorPaletteTokens,
        lightTokens = LightSnowColorPaletteTokens
    )

    WeatherConditionCategory.FOG -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkFogColorPaletteTokens,
        lightTokens = LightFogColorPaletteTokens
    )

    WeatherConditionCategory.FALLBACK -> colorPalette(
        darkTheme = darkTheme,
        darkTokens = DarkSunnyDayColorPaletteTokens,
        lightTokens = LightSunnyDayColorPaletteTokens
    )

}

@Stable
@Composable
fun ColorPalette.asAnimatedColorPalette(): ColorPalette {

    val animatedPrimary by animateColor(this.primary)
    val animatedOnPrimary by animateColor(this.onPrimary)
    val animatedSecondary by animateColor(this.secondary)
    val animatedOnSecondary by animateColor(this.onSecondary)
    val animatedSurfaceLow by animateColor(this.surfaceLow)
    val animatedOnSurfaceLow by animateColor(this.onSurfaceLow)
    val animatedSurfaceMedium by animateColor(this.surfaceMedium)
    val animatedOnSurfaceMedium by animateColor(this.onSurfaceMedium)
    val animatedSurfaceHigh by animateColor(this.surfaceHigh)
    val animatedOnSurfaceHigh by animateColor(this.onSurfaceHigh)
    val animatedError by animateColor(this.error)
    val animatedConditionPrimary by animateColor(this.conditionPrimary)
    val animatedConditionSecondary by animateColor(this.conditionSecondary)

    return ColorPalette(
        primary = animatedPrimary,
        onPrimary = animatedOnPrimary,
        secondary = animatedSecondary,
        onSecondary = animatedOnSecondary,
        surfaceLow = animatedSurfaceLow,
        onSurfaceLow = animatedOnSurfaceLow,
        surfaceMedium = animatedSurfaceMedium,
        onSurfaceMedium = animatedOnSurfaceMedium,
        surfaceHigh = animatedSurfaceHigh,
        onSurfaceHigh = animatedOnSurfaceHigh,
        error = animatedError,
        conditionPrimary = animatedConditionPrimary,
        conditionSecondary = animatedConditionSecondary
    )

}

@Stable
@Composable
private fun animateColor(color: Color): State<Color> {
    return animateColorAsState(
        targetValue = color,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
}

val LocalColorPalette = compositionLocalOf {
    colorPalette(
        darkTheme = true,
        darkTokens = DarkSunnyDayColorPaletteTokens,
        lightTokens = LightSunnyDayColorPaletteTokens
    )
}

val LocalColor = compositionLocalOf {
    colorPalette(
        darkTheme = true,
        darkTokens = DarkSunnyDayColorPaletteTokens,
        lightTokens = LightSunnyDayColorPaletteTokens
    ).onSurfaceLow
}