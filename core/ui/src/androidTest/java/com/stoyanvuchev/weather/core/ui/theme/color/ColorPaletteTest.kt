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

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import com.stoyanvuchev.weather.domain.other.WeatherConditionCategory
import com.stoyanvuchev.weather.domain.other.category
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ColorPaletteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun categories_darkAndLight_animatedPalette_and_localPalette_matchTokens() {
        composeTestRule.setContent {

            val themes = listOf(true, false) // true = dark, false = light.

            // Loop over all categories.
            WeatherConditionCategory.entries.forEach { category ->

                // Pick a sample condition for this category.
                val sampleCondition = WeatherCondition.entries
                    .firstOrNull { it.category() == category }
                    ?: WeatherCondition.SUNNY // fallback in case category has no matching condition.

                themes.forEach { darkTheme ->

                    val palette = remember { sampleCondition.toColorPalette(darkTheme) }

                    // Animated palette
                    val animatedPalette by rememberUpdatedState(palette.asAnimatedColorPalette())
                    assertPaletteEquals(animatedPalette, palette)

                    // LocalColorPalette
                    CompositionLocalProvider(LocalColorPalette provides palette) {
                        assertPaletteEquals(LocalColorPalette.current, palette)
                    }

                }

            }

        }
    }

    @Test
    fun localColor_match_color() {
        composeTestRule.setContent {

            val color = remember {
                WeatherCondition.SUNNY
                    .toColorPalette(false)
                    .onSurfaceLow
            }

            CompositionLocalProvider(LocalColor provides color) {
                assertThat(LocalColor.current).isEqualTo(color)
            }

        }
    }

    private fun assertPaletteEquals(actual: ColorPalette, expected: ColorPalette) {
        assertThat(actual.primary).isEqualTo(expected.primary)
        assertThat(actual.onPrimary).isEqualTo(expected.onPrimary)
        assertThat(actual.secondary).isEqualTo(expected.secondary)
        assertThat(actual.onSecondary).isEqualTo(expected.onSecondary)
        assertThat(actual.surfaceLow).isEqualTo(expected.surfaceLow)
        assertThat(actual.onSurfaceLow).isEqualTo(expected.onSurfaceLow)
        assertThat(actual.surfaceMedium).isEqualTo(expected.surfaceMedium)
        assertThat(actual.onSurfaceMedium).isEqualTo(expected.onSurfaceMedium)
        assertThat(actual.surfaceHigh).isEqualTo(expected.surfaceHigh)
        assertThat(actual.onSurfaceHigh).isEqualTo(expected.onSurfaceHigh)
        assertThat(actual.error).isEqualTo(expected.error)
        assertThat(actual.conditionPrimary).isEqualTo(expected.conditionPrimary)
        assertThat(actual.conditionSecondary).isEqualTo(expected.conditionSecondary)
    }

}