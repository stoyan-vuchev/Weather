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

package com.stoyanvuchev.weather.core.ui.theme.shape

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp
import sv.lib.squircleshape.SquircleShape

@Immutable
object ShapeTokens {

    val large: ShapeData
        get() = ShapeData(
            shape = SquircleShape(percent = 100, smoothing = 10),
            topStartRadius = 999.dp,
            topEndRadius = 999.dp,
            bottomStartRadius = 999.dp,
            bottomEndRadius = 999.dp
        )

    val medium: ShapeData
        get() = ShapeData(
            shape = SquircleShape(radius = 20.dp, smoothing = 50),
            topStartRadius = 20.dp,
            topEndRadius = 20.dp,
            bottomStartRadius = 20.dp,
            bottomEndRadius = 20.dp
        )

    val small: ShapeData
        get() = ShapeData(
            shape = SquircleShape(radius = 10.dp, smoothing = 50),
            topStartRadius = 10.dp,
            topEndRadius = 10.dp,
            bottomStartRadius = 10.dp,
            bottomEndRadius = 10.dp
        )

}