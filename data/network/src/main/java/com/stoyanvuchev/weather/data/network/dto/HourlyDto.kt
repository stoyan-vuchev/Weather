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

package com.stoyanvuchev.weather.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    val dt: Int? = 0,
    val temp: Double? = 0.0,
    @SerialName("feels_like")
    val feelsLike: Double? = 0.0,
    val pressure: Int? = 0,
    val humidity: Int? = 0,
    @SerialName("dew_point")
    val dewPoint: Double? = 0.0,
    val uvi: Double? = 0.0,
    val clouds: Int? = 0,
    val visibility: Int? = 0,
    @SerialName("wind_speed")
    val windSpeed: Double? = 0.0,
    @SerialName("wind_deg")
    val windDeg: Double? = 0.0,
    @SerialName("wind_gust")
    val windGust: Double? = 0.0,
    val weather: List<WeatherDto>? = emptyList(),
    val pop: Double? = 0.0,
    val rain: RainDto? = RainDto()
)