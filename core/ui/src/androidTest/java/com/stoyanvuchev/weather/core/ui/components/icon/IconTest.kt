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

package com.stoyanvuchev.weather.core.ui.components.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stoyanvuchev.weather.core.ui.theme.ThemeWrapper
import com.stoyanvuchev.weather.core.ui.theme.color.toColorPalette
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IconTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun icon_displaysPainter() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Icon(
                    painter = ColorPainter(Color.Red),
                    contentDescription = "Red icon"
                )
            }
        }

        composeTestRule
            .onNode(hasContentDescription("Red icon"))
            .assertIsDisplayed()
            .assertIsEnabled()

    }

    @Test
    fun icon_setsCorrectSemanticsRole() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Icon(
                    painter = ColorPainter(Color.Green),
                    contentDescription = "Green icon"
                )
            }
        }

        composeTestRule
            .onNode(hasRole(Role.Image) and hasContentDescription("Green icon"))
            .assertExists()

    }

    @Test
    fun icon_withoutContentDescription_hasNoSemantics() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Icon(
                    painter = ColorPainter(Color.Blue),
                    contentDescription = null
                )
            }
        }

        composeTestRule
            .onNode(hasRole(Role.Image))
            .assertDoesNotExist()

    }

    @Test
    fun icon_appliesTintWithoutCrashing() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Icon(
                    painter = ColorPainter(Color.Black),
                    contentDescription = "Tinted",
                    tint = Color.Magenta
                )
            }
        }

        // Can’t directly assert tint, but ensures it composes safely.
        composeTestRule.onNode(hasContentDescription("Tinted")).assertExists()

    }

    @Composable
    private fun WithThemeWrapper(
        content: @Composable () -> Unit
    ) = ThemeWrapper(
        colorPalette = remember {
            WeatherCondition.SUNNY.toColorPalette(darkTheme = false)
        },
        content = content
    )

    private fun hasRole(
        expectedRole: Role
    ): SemanticsMatcher = SemanticsMatcher.expectValue(
        SemanticsProperties.Role,
        expectedRole
    )


}
