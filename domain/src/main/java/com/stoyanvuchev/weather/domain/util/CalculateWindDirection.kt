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

package com.stoyanvuchev.weather.domain.util

import com.stoyanvuchev.weather.domain.other.WindDirection

object CalculateWindDirection {

    fun of(value: Double?): WindDirection = if (value != null) {
        when (value) {
            in 11.25..33.75 -> WindDirection.NORTH_NORTH_EAST
            in 33.76..56.24 -> WindDirection.NORTH_EAST
            in 56.25..78.74 -> WindDirection.EAST_NORTH_EAST
            in 78.75..101.24 -> WindDirection.EAST
            in 101.25..123.74 -> WindDirection.EAST_SOUTH_EAST
            in 123.75..146.24 -> WindDirection.SOUTH_EAST
            in 146.25..168.74 -> WindDirection.SOUTH_SOUTH_EAST
            in 168.75..191.24 -> WindDirection.SOUTH
            in 191.25..213.74 -> WindDirection.SOUTH_SOUTH_WEST
            in 213.75..236.24 -> WindDirection.SOUTH_WEST
            in 236.25..258.74 -> WindDirection.WEST_SOUTH_WEST
            in 258.75..281.24 -> WindDirection.WEST
            in 281.25..303.74 -> WindDirection.WEST_NORTH_WEST
            in 303.75..326.24 -> WindDirection.NORTH_WEST
            in 326.25..348.74 -> WindDirection.NORTH_NORTH_WEST
            else -> WindDirection.NORTH
        }
    } else WindDirection.NORTH

}