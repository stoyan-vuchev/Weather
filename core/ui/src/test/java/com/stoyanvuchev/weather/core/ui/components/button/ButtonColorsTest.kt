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

import androidx.compose.ui.graphics.Color
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotEqualTo
import com.stoyanvuchev.weather.core.ui.theme.color.tokens.DarkSunnyDayColorPaletteTokens
import org.junit.Test

class ButtonColorsTest {

    private val enabledContainer = DarkSunnyDayColorPaletteTokens.primary
    private val enabledContent = DarkSunnyDayColorPaletteTokens.onPrimary
    private val disabledContainer = DarkSunnyDayColorPaletteTokens.primary.copy(.33f)
    private val disabledContent = DarkSunnyDayColorPaletteTokens.onPrimary.copy(.33f)

    private val buttonColors = ButtonColors(
        containerColor = enabledContainer,
        contentColor = enabledContent,
        disabledContainerColor = disabledContainer,
        disabledContentColor = disabledContent
    )

    @Test
    fun `containerColor returns correct color based on enabled`() {
        assertThat(buttonColors.containerColor(true)).isEqualTo(enabledContainer)
        assertThat(buttonColors.containerColor(false)).isEqualTo(disabledContainer)
    }

    @Test
    fun `contentColor returns correct color based on enabled`() {
        assertThat(buttonColors.contentColor(true)).isEqualTo(enabledContent)
        assertThat(buttonColors.contentColor(false)).isEqualTo(disabledContent)
    }

    @Test
    fun `equals returns true for same values`() {

        val sameColors = ButtonColors(
            containerColor = enabledContainer,
            contentColor = enabledContent,
            disabledContainerColor = disabledContainer,
            disabledContentColor = disabledContent
        )

        assertThat(sameColors).isEqualTo(buttonColors)
        assertThat(sameColors.hashCode()).isEqualTo(buttonColors.hashCode())

    }

    @Test
    fun `equals returns false for different values`() {

        val differentColors = ButtonColors(
            containerColor = Color.Blue,
            contentColor = enabledContent,
            disabledContainerColor = disabledContainer,
            disabledContentColor = disabledContent
        )

        assertThat(buttonColors == differentColors).isFalse()
        assertThat(differentColors.hashCode()).isNotEqualTo(buttonColors.hashCode())

    }

    @Test
    fun `equals returns false when compared with null or different type`() {
        assertThat(buttonColors.equals(null)).isFalse()
        assertThat(buttonColors.equals("NotAButtonColors")).isFalse()
    }

    @Test
    fun `hashCode consistency`() {

        val sameColors = ButtonColors(
            containerColor = enabledContainer,
            contentColor = enabledContent,
            disabledContainerColor = disabledContainer,
            disabledContentColor = disabledContent
        )

        repeat(10) {
            assertThat(sameColors.hashCode()).isEqualTo(buttonColors.hashCode())
        }

    }

}
