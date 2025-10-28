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

package com.stoyanvuchev.weather.domain.other

enum class WeatherCondition {

    // Clear Sky
    SUNNY,
    CLEAR,

    // Clouds
    MOSTLY_SUNNY,
    MOSTLY_CLEAR,
    PARTLY_CLOUDY_DAY,
    PARTLY_CLOUDY_NIGHT,
    MOSTLY_CLOUDY_DAY,
    MOSTLY_CLOUDY_NIGHT,
    CLOUDY_DAY,
    CLOUDY_NIGHT,

    // Drizzle
    LIGHT_DRIZZLE,
    DRIZZLE,
    INTENSIVE_DRIZZLE,
    HEAVY_DRIZZLE,

    // Rain
    LIGHT_RAIN,
    RAIN,
    RAIN_SHOWERS,
    INTENSIVE_RAIN,
    HEAVY_RAIN,

    // Thunderstorm
    LIGHT_THUNDERSTORM_DAY,
    LIGHT_THUNDERSTORM_NIGHT,
    THUNDERSTORM,
    HEAVY_THUNDERSTORM,

    // Snow
    LIGHT_SNOW,
    SNOW,
    HEAVY_SNOW,
    SLEET,
    LIGHT_RAIN_AND_SNOW,
    RAIN_AND_SNOW,

    // Atmosphere
    MIST,
    SMOG,
    HAZE,
    SAND_DUST_WHIRLS,
    FOG,
    SAND_STORM,
    DUST_STORM,
    VOLCANIC_ASH,
    SQUALLS,
    TORNADO,

    // Error
    ERROR

}