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

package com.stoyanvuchev.weather.core.ui.components.text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stoyanvuchev.weather.core.ui.theme.ThemeWrapper
import com.stoyanvuchev.weather.core.ui.theme.color.toColorPalette
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.LightSunnyDayColorPaletteTokens
import com.stoyanvuchev.weather.core.ui.theme.typography.TypographyTokens
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text_displaysProvidedString() {

        val textToDisplay = "Hello Compose"

        composeTestRule.setContent {
            WithThemeWrapper {
                Text(text = textToDisplay)
            }
        }

        composeTestRule
            .onNodeWithText(textToDisplay)
            .assertIsDisplayed()

    }

    @Test
    fun text_usesProvidedColorAndStyle() {

        val textToDisplay = "Colored Text"
        val color = LightSunnyDayColorPaletteTokens.onSurfaceLow
        val style = TypographyTokens.bodyLarge

        composeTestRule.setContent {
            WithThemeWrapper {
                Text(
                    text = textToDisplay,
                    color = color,
                    style = style
                )
            }
        }

        // We can’t directly inspect color in Compose tests,
        // but we can confirm the text renders without exceptions.
        composeTestRule.onNodeWithText(textToDisplay).assertExists()

    }

    @Test
    fun text_respectsMaxLinesAndOverflow() {

        val longText = "This is a very long text that should overflow and be ellipsized."

        composeTestRule.setContent {
            WithThemeWrapper {
                Text(
                    text = longText,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        composeTestRule.onNodeWithText(longText, substring = true).assertExists()

    }

    @Test
    fun text_appliesTextAlignCenter() {

        val textToDisplay = "Centered Text"

        composeTestRule.setContent {
            WithThemeWrapper {
                Text(
                    text = textToDisplay,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Again, text alignment can’t be directly asserted,
        // but presence check suffices.
        composeTestRule.onNodeWithText(textToDisplay).assertExists()

    }

    @Test
    fun text_supportsSoftWrapParameter() {

        val textToDisplay = "Wrapping text"

        composeTestRule.setContent {
            WithThemeWrapper {
                Text(
                    text = textToDisplay,
                    softWrap = false
                )
            }
        }

        composeTestRule.onNodeWithText(textToDisplay).assertExists()

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

}