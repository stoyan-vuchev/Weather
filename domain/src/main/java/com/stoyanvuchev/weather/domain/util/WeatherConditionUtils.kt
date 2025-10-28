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

import com.stoyanvuchev.weather.domain.other.WeatherCondition

object WeatherConditionUtils {

    fun getWeatherCondition(
        conditionId: Int,
        conditionIcon: String
    ): WeatherCondition {

        val isDay = if (conditionIcon.isNotBlank()) conditionIcon.last().toString() == "d" else true

        return when (conditionId) {

            // Group 2xx: Thunderstorm

            in 200..202 -> WeatherCondition.THUNDERSTORM

            210 -> if (isDay) WeatherCondition.LIGHT_THUNDERSTORM_DAY
            else WeatherCondition.LIGHT_THUNDERSTORM_NIGHT

            211 -> WeatherCondition.THUNDERSTORM
            212 -> WeatherCondition.HEAVY_THUNDERSTORM
            221 -> WeatherCondition.HEAVY_THUNDERSTORM
            in 230..232 -> WeatherCondition.THUNDERSTORM

            // Group 3xx: Drizzle

            300 -> WeatherCondition.LIGHT_DRIZZLE
            301 -> WeatherCondition.DRIZZLE
            302 -> WeatherCondition.HEAVY_DRIZZLE
            310 -> WeatherCondition.INTENSIVE_DRIZZLE
            311 -> WeatherCondition.DRIZZLE
            312 -> WeatherCondition.HEAVY_DRIZZLE
            313 -> WeatherCondition.DRIZZLE
            314 -> WeatherCondition.HEAVY_DRIZZLE
            315 -> WeatherCondition.DRIZZLE

            // Group 5xx: Rain

            500 -> WeatherCondition.LIGHT_RAIN
            501 -> WeatherCondition.RAIN
            502 -> WeatherCondition.INTENSIVE_RAIN
            in 503..504 -> WeatherCondition.HEAVY_RAIN
            511 -> WeatherCondition.RAIN_AND_SNOW
            in 520..522 -> WeatherCondition.RAIN_SHOWERS
            531 -> WeatherCondition.RAIN

            // Group 6xx: Snow

            600 -> WeatherCondition.LIGHT_SNOW
            601 -> WeatherCondition.SNOW
            602 -> WeatherCondition.HEAVY_SNOW
            in 611..613 -> WeatherCondition.SLEET
            615 -> WeatherCondition.LIGHT_RAIN_AND_SNOW
            616 -> WeatherCondition.RAIN_AND_SNOW
            620 -> WeatherCondition.LIGHT_SNOW
            621 -> WeatherCondition.SNOW
            622 -> WeatherCondition.HEAVY_SNOW

            // Group 7xx: Atmosphere

            701 -> WeatherCondition.MIST
            711 -> WeatherCondition.SMOG
            721 -> WeatherCondition.HAZE
            731 -> WeatherCondition.SAND_DUST_WHIRLS
            741 -> WeatherCondition.FOG
            751 -> WeatherCondition.SAND_STORM
            761 -> WeatherCondition.DUST_STORM
            762 -> WeatherCondition.VOLCANIC_ASH
            771 -> WeatherCondition.SQUALLS
            781 -> WeatherCondition.TORNADO

            // Group 800: Clear Sky

            800 -> if (isDay) WeatherCondition.SUNNY else WeatherCondition.CLEAR

            // Group 80x: Clouds

            801 -> if (isDay) WeatherCondition.MOSTLY_SUNNY
            else WeatherCondition.MOSTLY_CLEAR

            802 -> if (isDay) WeatherCondition.PARTLY_CLOUDY_DAY
            else WeatherCondition.PARTLY_CLOUDY_NIGHT

            803 -> if (isDay) WeatherCondition.MOSTLY_CLOUDY_DAY
            else WeatherCondition.MOSTLY_CLOUDY_NIGHT

            804 -> if (isDay) WeatherCondition.CLOUDY_DAY
            else WeatherCondition.CLOUDY_NIGHT

            // Error

            else -> WeatherCondition.ERROR

        }

    }

}