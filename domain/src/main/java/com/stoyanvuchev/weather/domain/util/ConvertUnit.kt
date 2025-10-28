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

import com.stoyanvuchev.weather.domain.other.unit.DistanceUnit
import com.stoyanvuchev.weather.domain.other.unit.PressureUnit
import com.stoyanvuchev.weather.domain.other.unit.SpeedUnit
import com.stoyanvuchev.weather.domain.other.unit.TemperatureUnit
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object ConvertUnit {

    fun temperature(
        value: Float?,
        from: TemperatureUnit = TemperatureUnit.CELSIUS,
        to: TemperatureUnit
    ): Int {

        return if (value != null) when (from) {
            TemperatureUnit.CELSIUS if to == TemperatureUnit.CELSIUS -> value
            TemperatureUnit.CELSIUS if to == TemperatureUnit.FAHRENHEIT -> ((value * 9f / 5f) + 32f)
            TemperatureUnit.FAHRENHEIT if to == TemperatureUnit.FAHRENHEIT -> value
            TemperatureUnit.FAHRENHEIT if to == TemperatureUnit.CELSIUS -> ((value - 32f) * 9f / 5f)
            else -> throw IllegalArgumentException("Invalid temperature unit: from $from to $to")
        }.roundToInt() else 0

    }

    fun distance(
        value: Float?,
        from: DistanceUnit = DistanceUnit.KILOMETER,
        to: DistanceUnit
    ): Long {

        val distanceFactor = when (from) {
            DistanceUnit.KILOMETER if to == DistanceUnit.KILOMETER -> 1f
            DistanceUnit.KILOMETER if to == DistanceUnit.MILE -> 0.621371f
            DistanceUnit.MILE if to == DistanceUnit.KILOMETER -> 1.609344f
            DistanceUnit.MILE if to == DistanceUnit.MILE -> 1f
            else -> throw IllegalArgumentException("Invalid distance unit: from $from to $to")
        }

        return if (value != null) (value * distanceFactor).roundToLong() else 0L

    }

    fun speed(
        value: Float?,
        from: SpeedUnit = SpeedUnit.METER_PER_SECOND,
        to: SpeedUnit
    ): Long {

        val factor = when (from) {
            SpeedUnit.METER_PER_SECOND if to == SpeedUnit.KILOMETERS_PER_HOUR -> 3.6f
            SpeedUnit.METER_PER_SECOND if to == SpeedUnit.MILES_PER_HOUR -> 2.236936f
            SpeedUnit.METER_PER_SECOND if to == SpeedUnit.METER_PER_SECOND -> 1f
            SpeedUnit.METER_PER_SECOND if to == SpeedUnit.FOOT_PER_SECOND -> 3.28084f
            SpeedUnit.METER_PER_SECOND if to == SpeedUnit.KNOT -> 1.943844f
            SpeedUnit.KILOMETERS_PER_HOUR if to == SpeedUnit.KILOMETERS_PER_HOUR -> 1f
            SpeedUnit.KILOMETERS_PER_HOUR if to == SpeedUnit.MILES_PER_HOUR -> 0.621371f
            SpeedUnit.KILOMETERS_PER_HOUR if to == SpeedUnit.METER_PER_SECOND -> 0.277778f
            SpeedUnit.KILOMETERS_PER_HOUR if to == SpeedUnit.FOOT_PER_SECOND -> 0.911344f
            SpeedUnit.KILOMETERS_PER_HOUR if to == SpeedUnit.KNOT -> 0.539957f
            SpeedUnit.MILES_PER_HOUR if to == SpeedUnit.KILOMETERS_PER_HOUR -> 1.609344f
            SpeedUnit.MILES_PER_HOUR if to == SpeedUnit.MILES_PER_HOUR -> 1f
            SpeedUnit.MILES_PER_HOUR if to == SpeedUnit.METER_PER_SECOND -> 0.44704f
            SpeedUnit.MILES_PER_HOUR if to == SpeedUnit.FOOT_PER_SECOND -> 1.466667f
            SpeedUnit.MILES_PER_HOUR if to == SpeedUnit.KNOT -> 0.868976f
            SpeedUnit.FOOT_PER_SECOND if to == SpeedUnit.KILOMETERS_PER_HOUR -> 1.09728f
            SpeedUnit.FOOT_PER_SECOND if to == SpeedUnit.MILES_PER_HOUR -> 0.681818f
            SpeedUnit.FOOT_PER_SECOND if to == SpeedUnit.METER_PER_SECOND -> 0.3048f
            SpeedUnit.FOOT_PER_SECOND if to == SpeedUnit.FOOT_PER_SECOND -> 1f
            SpeedUnit.FOOT_PER_SECOND if to == SpeedUnit.KNOT -> 0.592484f
            SpeedUnit.KNOT if to == SpeedUnit.KILOMETERS_PER_HOUR -> 1.852f
            SpeedUnit.KNOT if to == SpeedUnit.MILES_PER_HOUR -> 1.150779f
            SpeedUnit.KNOT if to == SpeedUnit.METER_PER_SECOND -> 0.514444f
            SpeedUnit.KNOT if to == SpeedUnit.FOOT_PER_SECOND -> 1.68781f
            SpeedUnit.KNOT if to == SpeedUnit.KNOT -> 1f
            else -> throw IllegalArgumentException("Invalid speed unit: from $from to $to")
        }

        return if (value != null) (value * factor).roundToLong() else 0L

    }

    fun pressure(
        value: Int?,
        from: PressureUnit = PressureUnit.MILLIBARS,
        to: PressureUnit
    ): Int {

        return if (value != null) when (from) {
            PressureUnit.MILLIBARS if to == PressureUnit.MILLIBARS -> value
            PressureUnit.MILLIBARS if to == PressureUnit.INCHES -> (value / 33.864).roundToInt()
            PressureUnit.INCHES if to == PressureUnit.INCHES -> value
            PressureUnit.INCHES if to == PressureUnit.MILLIBARS -> (value * 33.864).roundToInt()
            else -> throw IllegalArgumentException("Invalid pressure unit: from $from to $to")
        } else 0

    }

}