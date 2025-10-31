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

package com.stoyanvuchev.weather.core.ui.theme

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.stoyanvuchev.weather.core.ui.theme.color.LocalColorPalette
import com.stoyanvuchev.weather.core.ui.theme.color.toColorPalette
import com.stoyanvuchev.weather.core.ui.theme.shape.LocalShapes
import com.stoyanvuchev.weather.core.ui.theme.shape.Shapes
import com.stoyanvuchev.weather.core.ui.theme.typography.LocalTypography
import com.stoyanvuchev.weather.core.ui.theme.typography.Typography
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ThemeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun theme_provides_colors_typography_and_shapes() {
        composeTestRule.setContent {

            val colorPalette = remember { WeatherCondition.SUNNY.toColorPalette(false) }
            val typography = remember { Typography() }
            val shapes = remember { Shapes() }

            CompositionLocalProvider(
                LocalColorPalette provides colorPalette,
                LocalTypography provides typography,
                LocalShapes provides shapes
            ) {

                // Colors

                assertThat(Theme.colorPalette).isEqualTo(colorPalette)
                assertThat(Theme.colorPalette.primary).isEqualTo(colorPalette.primary)
                assertThat(Theme.colorPalette.onPrimary).isEqualTo(colorPalette.onPrimary)
                assertThat(Theme.colorPalette.secondary).isEqualTo(colorPalette.secondary)
                assertThat(Theme.colorPalette.onSecondary).isEqualTo(colorPalette.onSecondary)
                assertThat(Theme.colorPalette.surfaceLow).isEqualTo(colorPalette.surfaceLow)
                assertThat(Theme.colorPalette.onSurfaceLow).isEqualTo(colorPalette.onSurfaceLow)
                assertThat(Theme.colorPalette.surfaceMedium).isEqualTo(colorPalette.surfaceMedium)
                assertThat(Theme.colorPalette.onSurfaceMedium).isEqualTo(colorPalette.onSurfaceMedium)
                assertThat(Theme.colorPalette.surfaceHigh).isEqualTo(colorPalette.surfaceHigh)
                assertThat(Theme.colorPalette.onSurfaceHigh).isEqualTo(colorPalette.onSurfaceHigh)
                assertThat(Theme.colorPalette.error).isEqualTo(colorPalette.error)
                assertThat(Theme.colorPalette.conditionPrimary).isEqualTo(colorPalette.conditionPrimary)
                assertThat(Theme.colorPalette.conditionSecondary).isEqualTo(colorPalette.conditionSecondary)

                // Typography

                assertThat(Theme.typography).isEqualTo(typography)
                assertThat(Theme.typography.displayLarge).isEqualTo(typography.displayLarge)
                assertThat(Theme.typography.titleLarge).isEqualTo(typography.titleLarge)
                assertThat(Theme.typography.titleSmall).isEqualTo(typography.titleSmall)
                assertThat(Theme.typography.bodyLarge).isEqualTo(typography.bodyLarge)
                assertThat(Theme.typography.bodyMedium).isEqualTo(typography.bodyMedium)
                assertThat(Theme.typography.bodySmall).isEqualTo(typography.bodySmall)
                assertThat(Theme.typography.labelLarge).isEqualTo(typography.labelLarge)
                assertThat(Theme.typography.labelMedium).isEqualTo(typography.labelMedium)
                assertThat(Theme.typography.labelSmall).isEqualTo(typography.labelSmall)

                // Shapes

                assertThat(Theme.shapes).isEqualTo(shapes)
                assertThat(Theme.shapes.large).isEqualTo(shapes.large)
                assertThat(Theme.shapes.medium).isEqualTo(shapes.medium)
                assertThat(Theme.shapes.small).isEqualTo(shapes.small)

            }

        }
    }

}