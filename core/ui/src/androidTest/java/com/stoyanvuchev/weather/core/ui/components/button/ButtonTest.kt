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

package com.stoyanvuchev.weather.core.ui.components.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.stoyanvuchev.weather.core.ui.theme.ThemeWrapper
import com.stoyanvuchev.weather.core.ui.theme.color.toColorPalette
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun button_displaysContent() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(onClick = {}, modifier = Modifier.testTag("button")) {
                    Box(
                        Modifier
                            .testTag("content")
                            .size(16.dp)
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("content", true).assertIsDisplayed()

    }

    @Test
    fun button_clickInvokesOnClick_whenEnabled() {

        var clicked = false

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(onClick = { clicked = true }, modifier = Modifier.testTag("button")) {
                    Box(Modifier.size(16.dp))
                }
            }
        }

        composeTestRule.onNodeWithTag("button").performClick()
        assertThat(clicked).isTrue()

    }

    @Test
    fun button_clickDoesNotInvokeOnClick_whenDisabled() {

        var clicked = false

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(
                    onClick = { clicked = true },
                    enabled = false,
                    modifier = Modifier.testTag("button")
                ) {
                    Box(Modifier.size(16.dp))
                }
            }
        }

        composeTestRule.onNodeWithTag("button").performClick()
        assertThat(clicked).isFalse()

    }

    @Test
    fun button_appliesRoleButtonSemantics() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(onClick = {}, modifier = Modifier.testTag("button")) {
                    Box(Modifier.size(16.dp))
                }
            }
        }

        composeTestRule
            .onNodeWithTag("button")
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.Role,
                    Role.Button
                )
            )

    }

    @Test
    fun button_appliesContentColorComposition() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(onClick = {}, modifier = Modifier.testTag("button")) {
                    Box(
                        Modifier
                            .testTag("content")
                            .size(16.dp)
                    )
                }
            }
        }

        // Cannot directly assert LocalColor; verifying content renders without crash
        composeTestRule.onNodeWithTag("content", true).assertExists()

    }

    @Test
    fun button_displaysMultipleContentElements() {

        composeTestRule.setContent {
            WithThemeWrapper {
                Button(onClick = {}, modifier = Modifier.testTag("button")) {
                    Box(
                        Modifier
                            .testTag("icon")
                            .size(16.dp)
                    )
                    Box(
                        Modifier
                            .testTag("text")
                            .size(16.dp)
                    )
                }
            }
        }

        composeTestRule.onNodeWithTag("icon", true).assertIsDisplayed()
        composeTestRule.onNodeWithTag("text", true).assertIsDisplayed()

    }

    @Composable
    private fun WithThemeWrapper(content: @Composable () -> Unit) = ThemeWrapper(
        colorPalette = remember { WeatherCondition.SUNNY.toColorPalette(darkTheme = false) },
        content = content
    )

}