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

package com.stoyanvuchev.weather.core.ui.theme.color.tokens

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
object LightFogColorPaletteTokens : ColorPaletteTokens {
    override val primary: Color get() = Color(0xFFB3D4E5)
    override val onPrimary: Color get() = Color(0xFF131C20)
    override val secondary: Color get() = Color(0xFFB3DDE5)
    override val onSecondary: Color get() = Color(0xFF131E20)
    override val surfaceLow: Color get() = Color(0xFFEFF1F5)
    override val onSurfaceLow: Color get() = Color(0xFF101518)
    override val surfaceMedium: Color get() = Color(0x80FFFFFF)
    override val onSurfaceMedium: Color get() = Color(0xFF101518)
    override val surfaceHigh: Color get() = Color(0xFFB3C4CC)
    override val onSurfaceHigh: Color get() = Color(0xFF101518)
    override val error: Color get() = Color(0xFFBF4040)
    override val conditionPrimary: Color get() = Color(0xFF2A9CD5)
    override val conditionSecondary: Color get() = Color(0xFF2AB8D5)
}

@Immutable
object DarkFogColorPaletteTokens : ColorPaletteTokens {
    override val primary: Color get() = Color(0xFF29363D)
    override val onPrimary: Color get() = Color(0xFFEFF3F5)
    override val secondary: Color get() = Color(0xFF293A3D)
    override val onSecondary: Color get() = Color(0xFFEFF4F5)
    override val surfaceLow: Color get() = Color(0xFF000000)
    override val onSurfaceLow: Color get() = Color(0xFFEFF3F5)
    override val surfaceMedium: Color get() = Color(0x14B3C4CC)
    override val onSurfaceMedium: Color get() = Color(0xFFEFF3F5)
    override val surfaceHigh: Color get() = Color(0x29B3C4CC)
    override val onSurfaceHigh: Color get() = Color(0xFFEFF3F5)
    override val error: Color get() = Color(0xFFD06868)
    override val conditionPrimary: Color get() = Color(0xFF2A9CD5)
    override val conditionSecondary: Color get() = Color(0xFF2AB8D5)
}