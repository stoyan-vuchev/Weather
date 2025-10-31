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

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.stoyanvuchev.weather.core.ui.theme.color.ColorPalette
import com.stoyanvuchev.weather.core.ui.theme.color.LocalColorPalette
import com.stoyanvuchev.weather.core.ui.theme.color.asAnimatedColorPalette
import com.stoyanvuchev.weather.core.ui.theme.shape.LocalShapes
import com.stoyanvuchev.weather.core.ui.theme.shape.Shapes
import com.stoyanvuchev.weather.core.ui.theme.typography.LocalTypography
import com.stoyanvuchev.weather.core.ui.theme.typography.Typography

@Composable
fun ThemeWrapper(
    colorPalette: ColorPalette,
    typography: Typography,
    shapes: Shapes,
    content: @Composable () -> Unit
) {

    val animatedColorPalette by rememberUpdatedState(
        colorPalette.asAnimatedColorPalette()
    )

    CompositionLocalProvider(
        LocalColorPalette provides animatedColorPalette,
        LocalTypography provides typography,
        LocalTypography provides typography,
        LocalShapes provides shapes,
        content = content
    )

}