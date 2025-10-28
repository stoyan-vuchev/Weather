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

package com.stoyanvuchev.weather.data.repository.mappers

import com.stoyanvuchev.weather.data.local.database.entity.CurrentEntity
import com.stoyanvuchev.weather.data.local.database.entity.DailyEntity
import com.stoyanvuchev.weather.data.local.database.entity.FeelsLikeEntity
import com.stoyanvuchev.weather.data.local.database.entity.HourlyEntity
import com.stoyanvuchev.weather.data.local.database.entity.TempEntity
import com.stoyanvuchev.weather.data.local.database.entity.WeatherEntity
import com.stoyanvuchev.weather.domain.model.moon.MoonModel
import com.stoyanvuchev.weather.domain.model.sun.SunModel
import com.stoyanvuchev.weather.domain.model.weather.CurrentWeatherModel
import com.stoyanvuchev.weather.domain.model.weather.DailyModel
import com.stoyanvuchev.weather.domain.model.weather.FeelsLikeModel
import com.stoyanvuchev.weather.domain.model.weather.HourlyModel
import com.stoyanvuchev.weather.domain.model.weather.TempModel
import com.stoyanvuchev.weather.domain.model.weather.WeatherModel
import com.stoyanvuchev.weather.domain.model.wind.WindModel
import com.stoyanvuchev.weather.domain.other.unit.TemperatureUnit
import com.stoyanvuchev.weather.domain.other.unit.UnitConfiguration
import com.stoyanvuchev.weather.domain.util.AngleFractionCalculator
import com.stoyanvuchev.weather.domain.util.CalculateMoonPhase
import com.stoyanvuchev.weather.domain.util.UnitPicker
import kotlin.math.roundToInt

fun WeatherEntity.toModel(
    unitConfiguration: UnitConfiguration
) = WeatherModel(
    lat = lat,
    lon = lon,
    name = name,
    timestamp = System.currentTimeMillis().toString(),
    timezone = timezone,
    timezoneOffset = timezoneOffset,
    current = current.toModel(timezone, unitConfiguration),
    hourly = hourly.map { it.toModel(timezone, unitConfiguration) },
    daily = daily.map { it.toModel(timezone, unitConfiguration) }
)

fun CurrentEntity.toModel(
    timezone: String,
    unitConfiguration: UnitConfiguration
) = CurrentWeatherModel(
    dateAndTime = dt,
    timezone = timezone,
    sun = SunModel(
        sunrise = sunrise,
        sunset = sunset,
        timezone = timezone,
        angleFraction = AngleFractionCalculator.calculateFraction(
            start = sunrise ?: 0L,
            end = sunset ?: 0L,
            current = dt ?: 0L
        )
    ),
    moon = MoonModel(
        moonrise = moonrise,
        moonset = moonset,
        timezone = timezone,
        angleFraction = AngleFractionCalculator.calculateFraction(
            start = moonrise ?: 0L,
            end = moonset ?: 0L,
            current = dt ?: 0L
        ),
        phase = CalculateMoonPhase.of(moonPhase)
    ),
    temperature = UnitPicker.temperature(
        tempCelsius = tempCelsius,
        tempFahrenheit = tempFahrenheit,
        temperatureUnit = unitConfiguration.temperatureUnit
    ),
    feelsLike = UnitPicker.temperature(
        tempCelsius = feelsLikeCelsius,
        tempFahrenheit = feelsLikeFahrenheit,
        temperatureUnit = unitConfiguration.temperatureUnit
    ),
    pressure = UnitPicker.pressure(
        pressureMillibars = pressureMillibars,
        pressureInches = pressureInches,
        pressureUnit = unitConfiguration.pressureUnit
    ),
    humidity = humidity,
    dewPoint = dewPoint,
    uvIndex = uvIndex,
    clouds = clouds,
    visibility = UnitPicker.distance(
        distanceKilometers = visibilityKilometers,
        distanceMiles = visibilityMiles,
        distanceUnit = unitConfiguration.distanceUnit
    ),
    wind = WindModel(
        windSpeed = UnitPicker.speed(
            speedKilometersPerHour = windSpeedKmh,
            speedMilesPerHour = windSpeedMph,
            speedMeterPerSecond = windSpeedMs,
            speedFootPerSecond = windSpeedFt,
            speedKnot = windSpeedKt,
            speedUnit = unitConfiguration.speedUnit
        ),
        windDeg = windDeg,
        windDirection = windDirection,
        windGust = UnitPicker.speed(
            speedKilometersPerHour = windGustKmh,
            speedMilesPerHour = windGustMph,
            speedMeterPerSecond = windGustMs,
            speedFootPerSecond = windGustFt,
            speedKnot = windGustKt,
            speedUnit = unitConfiguration.speedUnit
        )
    ),
    weatherCondition = weatherCondition,
    rain = rain,
    snow = snow
)

fun HourlyEntity.toModel(
    timezone: String,
    unitConfiguration: UnitConfiguration
) = HourlyModel(
    dateAndTime = dt,
    timezone = timezone,
    temp = UnitPicker.temperature(
        tempCelsius = tempCelsius,
        tempFahrenheit = tempFahrenheit,
        temperatureUnit = unitConfiguration.temperatureUnit
    ),
    feelsLike = UnitPicker.temperature(
        tempCelsius = feelsLikeCelsius,
        tempFahrenheit = feelsLikeFahrenheit,
        temperatureUnit = unitConfiguration.temperatureUnit
    ),
    pressure = UnitPicker.pressure(
        pressureMillibars = pressureMillibars,
        pressureInches = pressureInches,
        pressureUnit = unitConfiguration.pressureUnit
    ),
    humidity = humidity,
    dewPoint = dewPoint,
    uvIndex = uvIndex,
    clouds = clouds,
    visibility = UnitPicker.distance(
        distanceKilometers = visibilityKilometers,
        distanceMiles = visibilityMiles,
        distanceUnit = unitConfiguration.distanceUnit
    ),
    wind = WindModel(
        windSpeed = UnitPicker.speed(
            speedKilometersPerHour = windSpeedKmh,
            speedMilesPerHour = windSpeedMph,
            speedMeterPerSecond = windSpeedMs,
            speedFootPerSecond = windSpeedFt,
            speedKnot = windSpeedKt,
            speedUnit = unitConfiguration.speedUnit
        ),
        windDeg = windDeg,
        windDirection = windDirection,
        windGust = UnitPicker.speed(
            speedKilometersPerHour = windGustKmh,
            speedMilesPerHour = windGustMph,
            speedMeterPerSecond = windGustMs,
            speedFootPerSecond = windGustFt,
            speedKnot = windGustKt,
            speedUnit = unitConfiguration.speedUnit
        )
    ),
    weatherCondition = weatherCondition,
    pop = pop,
    rain = rain,
    snow = snow
)

fun DailyEntity.toModel(
    timezone: String,
    unitConfiguration: UnitConfiguration
) = DailyModel(
    dateAndTime = dt,
    timezone = timezone,
    sun = SunModel(
        sunrise = sunrise,
        sunset = sunset,
        timezone = timezone,
        angleFraction = AngleFractionCalculator.calculateFraction(
            start = sunrise ?: 0L,
            end = sunset ?: 0L,
            current = dt ?: 0L
        )
    ),
    moon = MoonModel(
        moonrise = moonrise,
        moonset = moonset,
        timezone = timezone,
        angleFraction = AngleFractionCalculator.calculateFraction(
            start = moonrise ?: 0L,
            end = moonset ?: 0L,
            current = dt ?: 0L
        ),
        phase = CalculateMoonPhase.of(moonPhase)
    ),
    temp = temp.toModel(unitConfiguration.temperatureUnit),
    feelsLike = feelsLike.toModel(unitConfiguration.temperatureUnit),
    pressure = UnitPicker.pressure(
        pressureMillibars = pressureMillibars,
        pressureInches = pressureInches,
        pressureUnit = unitConfiguration.pressureUnit
    ),
    humidity = humidity,
    dewPoint = dewPoint,
    wind = WindModel(
        windSpeed = UnitPicker.speed(
            speedKilometersPerHour = windSpeedKmh,
            speedMilesPerHour = windSpeedMph,
            speedMeterPerSecond = windSpeedMs,
            speedFootPerSecond = windSpeedFt,
            speedKnot = windSpeedKt,
            speedUnit = unitConfiguration.speedUnit
        ),
        windDeg = windDeg,
        windDirection = windDirection,
        windGust = UnitPicker.speed(
            speedKilometersPerHour = windGustKmh,
            speedMilesPerHour = windGustMph,
            speedMeterPerSecond = windGustMs,
            speedFootPerSecond = windGustFt,
            speedKnot = windGustKt,
            speedUnit = unitConfiguration.speedUnit
        )
    ),
    weatherCondition = weatherCondition,
    clouds = clouds,
    pop = pop,
    rain = rain.roundToInt(),
    snow = snow.roundToInt(),
    uvIndex = uvIndex
)

fun TempEntity.toModel(
    temperatureUnit: TemperatureUnit
) = TempModel(
    day = UnitPicker.temperature(
        tempCelsius = dayCelsius,
        tempFahrenheit = dayFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    min = UnitPicker.temperature(
        tempCelsius = minCelsius,
        tempFahrenheit = minFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    max = UnitPicker.temperature(
        tempCelsius = maxCelsius,
        tempFahrenheit = maxFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    night = UnitPicker.temperature(
        tempCelsius = nightCelsius,
        tempFahrenheit = nightFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    eve = UnitPicker.temperature(
        tempCelsius = eveCelsius,
        tempFahrenheit = eveFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    morn = UnitPicker.temperature(
        tempCelsius = mornCelsius,
        tempFahrenheit = mornFahrenheit,
        temperatureUnit = temperatureUnit
    )
)

fun FeelsLikeEntity.toModel(
    temperatureUnit: TemperatureUnit
) = FeelsLikeModel(
    day = UnitPicker.temperature(
        tempCelsius = dayCelsius,
        tempFahrenheit = dayFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    night = UnitPicker.temperature(
        tempCelsius = nightCelsius,
        tempFahrenheit = nightFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    eve = UnitPicker.temperature(
        tempCelsius = eveCelsius,
        tempFahrenheit = eveFahrenheit,
        temperatureUnit = temperatureUnit
    ),
    morn = UnitPicker.temperature(
        tempCelsius = mornCelsius,
        tempFahrenheit = mornFahrenheit,
        temperatureUnit = temperatureUnit
    )
)