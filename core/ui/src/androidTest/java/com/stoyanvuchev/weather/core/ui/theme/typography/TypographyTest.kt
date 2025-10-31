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

package com.stoyanvuchev.weather.core.ui.theme.typography

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TypographyTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun localTypography_matches_typographyTokens() {
        composeTestRule.setContent {
            val typography = remember { Typography() }
            CompositionLocalProvider(LocalTypography provides typography) {
                assertThat(LocalTypography.current).isEqualTo(typography)
                assertThat(LocalTypography.current.displayLarge).isEqualTo(typography.displayLarge)
                assertThat(LocalTypography.current.titleLarge).isEqualTo(typography.titleLarge)
                assertThat(LocalTypography.current.titleSmall).isEqualTo(typography.titleSmall)
                assertThat(LocalTypography.current.bodyLarge).isEqualTo(typography.bodyLarge)
                assertThat(LocalTypography.current.bodyMedium).isEqualTo(typography.bodyMedium)
                assertThat(LocalTypography.current.bodySmall).isEqualTo(typography.bodySmall)
                assertThat(LocalTypography.current.labelLarge).isEqualTo(typography.labelLarge)
                assertThat(LocalTypography.current.labelMedium).isEqualTo(typography.labelMedium)
                assertThat(LocalTypography.current.labelSmall).isEqualTo(typography.labelSmall)
            }
        }
    }

    @Test
    fun localTextStyle_matches_textStyle() {
        composeTestRule.setContent {
            val textStyle = remember { TypographyTokens.bodyMedium }
            CompositionLocalProvider(LocalTextStyle provides textStyle) {
                assertThat(LocalTextStyle.current).isEqualTo(textStyle)
            }
        }
    }

}