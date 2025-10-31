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

package com.stoyanvuchev.weather.core.ui.components.button.iconbutton

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.stoyanvuchev.weather.core.ui.theme.ThemeWrapper
import com.stoyanvuchev.weather.core.ui.theme.color.toColorPalette
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class IconButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun iconButton_isDisplayed() = runTest {

        composeTestRule.setContent {
            WithThemeWrapper {
                IconButton(
                    onClick = {},
                    modifier = Modifier.testTag("iconButton")
                ) {
                    Box(Modifier.testTag("iconButtonContent"))
                }
            }
        }

        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("iconButton", true).assertIsDisplayed()

    }

    @Test
    fun iconButton_clickInvokesOnClick_whenEnabled() = runTest {

        var clicked = false

        composeTestRule.setContent {
            WithThemeWrapper {
                IconButton(
                    onClick = { clicked = true },
                    modifier = Modifier.testTag("iconButton")
                ) {
                    Box(Modifier.testTag("iconButtonContent"))
                }
            }
        }

        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("iconButton", true).performClick()

        composeTestRule.awaitIdle()
        assertThat(clicked).isTrue()

    }

    @Test
    fun iconButton_clickDoesNotInvokeOnClick_whenDisabled() = runTest {

        var clicked = false

        composeTestRule.setContent {
            WithThemeWrapper {
                IconButton(
                    onClick = { clicked = true },
                    modifier = Modifier.testTag("iconButton"),
                    enabled = false
                ) {
                    Box(Modifier.testTag("iconButtonContent"))
                }
            }
        }

        composeTestRule.awaitIdle()
        composeTestRule.onNodeWithTag("iconButton", true).performClick()

        composeTestRule.awaitIdle()
        assertThat(clicked).isFalse()

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
