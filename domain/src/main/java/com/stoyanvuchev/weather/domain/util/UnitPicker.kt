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

object UnitPicker {

    fun temperature(
        tempCelsius: Int,
        tempFahrenheit: Int,
        temperatureUnit: TemperatureUnit
    ): Int = when (temperatureUnit) {
        TemperatureUnit.CELSIUS -> tempCelsius
        TemperatureUnit.FAHRENHEIT -> tempFahrenheit
    }

    fun distance(
        distanceKilometers: Long,
        distanceMiles: Long,
        distanceUnit: DistanceUnit
    ): Long = when (distanceUnit) {
        DistanceUnit.KILOMETER -> distanceKilometers
        DistanceUnit.MILE -> distanceMiles
    }

    fun speed(
        speedKilometersPerHour: Long,
        speedMilesPerHour: Long,
        speedMeterPerSecond: Long,
        speedFootPerSecond: Long,
        speedKnot: Long,
        speedUnit: SpeedUnit
    ): Long = when (speedUnit) {
        SpeedUnit.KILOMETERS_PER_HOUR -> speedKilometersPerHour
        SpeedUnit.MILES_PER_HOUR -> speedMilesPerHour
        SpeedUnit.METER_PER_SECOND -> speedMeterPerSecond
        SpeedUnit.FOOT_PER_SECOND -> speedFootPerSecond
        SpeedUnit.KNOT -> speedKnot
    }

    fun pressure(
        pressureMillibars: Int,
        pressureInches: Int,
        pressureUnit: PressureUnit
    ): Int = when (pressureUnit) {
        PressureUnit.MILLIBARS -> pressureMillibars
        PressureUnit.INCHES -> pressureInches
    }

}