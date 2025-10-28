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

package com.stoyanvuchev.weather.data.local.database.entity

import com.stoyanvuchev.weather.domain.other.WeatherCondition
import com.stoyanvuchev.weather.domain.other.WindDirection
import kotlinx.serialization.Serializable

@Serializable
data class DailyEntity(
    val dt: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val moonrise: Long? = null,
    val moonset: Long? = null,
    val moonPhase: Double? = null,
    val temp: TempEntity = TempEntity(),
    val feelsLike: FeelsLikeEntity = FeelsLikeEntity(),
    val pressureMillibars: Int = 0,
    val pressureInches: Int = 0,
    val humidity: Int = 0,
    val dewPoint: Double = 0.0,
    val windSpeedMs: Long = 0L,
    val windSpeedKmh: Long = 0L,
    val windSpeedMph: Long = 0L,
    val windSpeedFt: Long = 0L,
    val windSpeedKt: Long = 0L,
    val windDeg: Double = 0.0,
    val windDirection: WindDirection = WindDirection.NORTH,
    val windGustMs: Long = 0L,
    val windGustKmh: Long = 0L,
    val windGustMph: Long = 0L,
    val windGustFt: Long = 0L,
    val windGustKt: Long = 0L,
    val weatherCondition: WeatherCondition = WeatherCondition.CLOUDY_DAY,
    val clouds: Int = 0,
    val pop: Double = 0.0,
    val rain: Double = 0.0,
    val snow: Double = 0.0,
    val uvIndex: Float = 0f
)