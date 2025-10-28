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

package com.stoyanvuchev.weather.domain.model.weather

import com.stoyanvuchev.weather.domain.model.moon.MoonModel
import com.stoyanvuchev.weather.domain.model.sun.SunModel
import com.stoyanvuchev.weather.domain.model.wind.WindModel
import com.stoyanvuchev.weather.domain.other.WeatherCondition
import kotlinx.serialization.Serializable

@Serializable
data class DailyModel(
    val dateAndTime: Long? = null,
    val timezone: String = "",
    val sun: SunModel = SunModel(),
    val moon: MoonModel = MoonModel(),
    val temp: TempModel = TempModel(),
    val feelsLike: FeelsLikeModel = FeelsLikeModel(),
    val pressure: Int = 0,
    val humidity: Int = 0,
    val dewPoint: Double = 0.0,
    val wind: WindModel = WindModel(),
    val weatherCondition: WeatherCondition = WeatherCondition.CLOUDY_DAY,
    val clouds: Int = 0,
    val pop: Double = 0.0,
    val rain: Int = 0,
    val snow: Int = 0,
    val uvIndex: Float = 0f
)