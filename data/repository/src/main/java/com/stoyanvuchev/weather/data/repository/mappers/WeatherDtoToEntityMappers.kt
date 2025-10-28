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
import com.stoyanvuchev.weather.data.network.dto.CurrentDto
import com.stoyanvuchev.weather.data.network.dto.DailyDto
import com.stoyanvuchev.weather.data.network.dto.FeelsLikeDto
import com.stoyanvuchev.weather.data.network.dto.HourlyDto
import com.stoyanvuchev.weather.data.network.dto.TempDto
import com.stoyanvuchev.weather.data.network.dto.WeatherDto
import com.stoyanvuchev.weather.data.network.dto.WeatherResponseDto
import com.stoyanvuchev.weather.domain.model.weather.RainModel
import com.stoyanvuchev.weather.domain.model.weather.SnowModel
import com.stoyanvuchev.weather.domain.other.unit.DistanceUnit
import com.stoyanvuchev.weather.domain.other.unit.PressureUnit
import com.stoyanvuchev.weather.domain.other.unit.SpeedUnit
import com.stoyanvuchev.weather.domain.other.unit.TemperatureUnit
import com.stoyanvuchev.weather.domain.util.CalculateWindDirection
import com.stoyanvuchev.weather.domain.util.ConvertUnit
import com.stoyanvuchev.weather.domain.util.WeatherConditionUtils

fun WeatherResponseDto.toEntity(): WeatherEntity {

    val newDaily = daily.map { it.toEntity() }
    val newHourly = hourly.subList(
        fromIndex = 0,
        toIndex = 23
    ).map { it.toEntity() }

    val newCurrent = current.toEntity(
        moonrise = newDaily.firstOrNull()?.moonrise,
        moonset = newDaily.firstOrNull()?.moonset,
        moonPhase = newDaily.firstOrNull()?.moonPhase
    )

    return WeatherEntity(
        lat = "$lat",
        lon = "$lon",
        name = "",
        timestamp = System.currentTimeMillis().toString(),
        timezone = timezone,
        timezoneOffset = timezoneOffset,
        current = newCurrent,
        hourly = newHourly,
        daily = newDaily
    )

}

fun CurrentDto.toEntity(
    moonrise: Long?,
    moonset: Long?,
    moonPhase: Double?
): CurrentEntity {
    val temperatureValue = temp?.toFloat()
    val feelsLikeValue = feelsLike?.toFloat()
    val visibilityValue = visibility?.toFloat()?.div(1000f)
    val windSpeedValue = windSpeed?.toFloat()
    val windGustValue = windGust?.toFloat()
    val weatherDto = weather?.firstOrNull() ?: WeatherDto()
    return CurrentEntity(
        dt = dt,
        sunrise = sunrise,
        sunset = sunset,
        moonrise = moonrise,
        moonset = moonset,
        moonPhase = moonPhase,
        tempCelsius = ConvertUnit.temperature(
            value = temperatureValue,
            to = TemperatureUnit.CELSIUS
        ),
        tempFahrenheit = ConvertUnit.temperature(
            value = temperatureValue,
            to = TemperatureUnit.FAHRENHEIT
        ),
        feelsLikeCelsius = ConvertUnit.temperature(
            value = feelsLikeValue,
            to = TemperatureUnit.CELSIUS
        ),
        feelsLikeFahrenheit = ConvertUnit.temperature(
            value = feelsLikeValue,
            to = TemperatureUnit.FAHRENHEIT
        ),
        pressureMillibars = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.MILLIBARS
        ),
        pressureInches = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.INCHES
        ),
        humidity = humidity ?: 0,
        dewPoint = dewPoint ?: 0.0,
        uvIndex = uvi?.toFloat() ?: 0f,
        clouds = clouds ?: 0,
        visibilityKilometers = ConvertUnit.distance(
            value = visibilityValue,
            to = DistanceUnit.KILOMETER
        ),
        visibilityMiles = ConvertUnit.distance(
            value = visibilityValue,
            to = DistanceUnit.MILE
        ),
        windSpeedMs = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windSpeedKmh = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windSpeedMph = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windSpeedFt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windSpeedKt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KNOT
        ),
        windDeg = windDeg ?: 0.0,
        windDirection = CalculateWindDirection.of(windDeg),
        windGustMs = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windGustKmh = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windGustMph = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windGustFt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windGustKt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KNOT
        ),
        weatherCondition = WeatherConditionUtils.getWeatherCondition(
            conditionId = weatherDto.id ?: 0,
            conditionIcon = weatherDto.icon ?: ""
        ),
        rain = rain?.toModel() ?: RainModel(),
        snow = snow?.toModel() ?: SnowModel()
    )
}

fun HourlyDto.toEntity(): HourlyEntity {
    val temperatureValue = temp?.toFloat()
    val feelsLikeValue = feelsLike?.toFloat()
    val visibilityValue = visibility?.toFloat()?.div(1000f)
    val windSpeedValue = windSpeed?.toFloat()
    val windGustValue = windGust?.toFloat()
    val weatherDto = weather?.firstOrNull() ?: WeatherDto()
    return HourlyEntity(
        dt = dt,
        tempCelsius = ConvertUnit.temperature(
            value = temperatureValue,
            to = TemperatureUnit.CELSIUS
        ),
        tempFahrenheit = ConvertUnit.temperature(
            value = temperatureValue,
            to = TemperatureUnit.FAHRENHEIT
        ),
        feelsLikeCelsius = ConvertUnit.temperature(
            value = feelsLikeValue,
            to = TemperatureUnit.CELSIUS
        ),
        feelsLikeFahrenheit = ConvertUnit.temperature(
            value = feelsLikeValue,
            to = TemperatureUnit.FAHRENHEIT
        ),
        pressureMillibars = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.MILLIBARS
        ),
        pressureInches = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.INCHES
        ),
        humidity = humidity ?: 0,
        dewPoint = dewPoint ?: 0.0,
        uvIndex = uvi?.toFloat() ?: 0f,
        clouds = clouds ?: 0,
        visibilityKilometers = ConvertUnit.distance(
            value = visibilityValue,
            to = DistanceUnit.KILOMETER
        ),
        visibilityMiles = ConvertUnit.distance(
            value = visibilityValue,
            to = DistanceUnit.MILE
        ),
        windSpeedMs = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windSpeedKmh = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windSpeedMph = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windSpeedFt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windSpeedKt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KNOT
        ),
        windDeg = windDeg ?: 0.0,
        windDirection = CalculateWindDirection.of(windDeg),
        windGustMs = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windGustKmh = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windGustMph = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windGustFt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windGustKt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KNOT
        ),
        weatherCondition = WeatherConditionUtils.getWeatherCondition(
            conditionId = weatherDto.id ?: 0,
            conditionIcon = weatherDto.icon ?: ""
        ),
        pop = pop ?: 0.0,
        rain = rain?.toModel() ?: RainModel(),
        snow = snow?.toModel() ?: SnowModel()
    )
}

fun DailyDto.toEntity(): DailyEntity {
    val windSpeedValue = windSpeed?.toFloat()
    val windGustValue = windGust?.toFloat()
    val weatherDto = weather?.firstOrNull() ?: WeatherDto()
    return DailyEntity(
        dt = dt,
        sunrise = sunrise,
        sunset = sunset,
        moonrise = moonrise,
        moonset = moonset,
        moonPhase = moonPhase,
        temp = temp?.toEntity() ?: TempEntity(),
        feelsLike = feelsLike?.toEntity() ?: FeelsLikeEntity(),
        pressureMillibars = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.MILLIBARS
        ),
        pressureInches = ConvertUnit.pressure(
            value = pressure,
            to = PressureUnit.INCHES
        ),
        humidity = humidity ?: 0,
        dewPoint = dewPoint ?: 0.0,
        windSpeedMs = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windSpeedKmh = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windSpeedMph = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windSpeedFt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windSpeedKt = ConvertUnit.speed(
            value = windSpeedValue,
            to = SpeedUnit.KNOT
        ),
        windDeg = windDeg ?: 0.0,
        windDirection = CalculateWindDirection.of(windDeg),
        windGustMs = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.METER_PER_SECOND
        ),
        windGustKmh = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KILOMETERS_PER_HOUR
        ),
        windGustMph = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.MILES_PER_HOUR
        ),
        windGustFt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.FOOT_PER_SECOND
        ),
        windGustKt = ConvertUnit.speed(
            value = windGustValue,
            to = SpeedUnit.KNOT
        ),
        weatherCondition = WeatherConditionUtils.getWeatherCondition(
            conditionId = weatherDto.id ?: 0,
            conditionIcon = weatherDto.icon ?: ""
        ),
        clouds = clouds ?: 0,
        pop = pop ?: 0.0,
        rain = rain ?: 0.0,
        snow = snow ?: 0.0,
        uvIndex = uvi?.toFloat() ?: 0f
    )
}

fun TempDto.toEntity() = TempEntity(
    dayCelsius = ConvertUnit.temperature(
        value = day?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    dayFahrenheit = ConvertUnit.temperature(
        value = day?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    minCelsius = ConvertUnit.temperature(
        value = min?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    minFahrenheit = ConvertUnit.temperature(
        value = min?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    maxCelsius = ConvertUnit.temperature(
        value = max?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    maxFahrenheit = ConvertUnit.temperature(
        value = max?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    nightCelsius = ConvertUnit.temperature(
        value = night?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    nightFahrenheit = ConvertUnit.temperature(
        value = night?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    eveCelsius = ConvertUnit.temperature(
        value = eve?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    eveFahrenheit = ConvertUnit.temperature(
        value = eve?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    mornCelsius = ConvertUnit.temperature(
        value = morn?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    mornFahrenheit = ConvertUnit.temperature(
        value = morn?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    )
)

fun FeelsLikeDto.toEntity() = FeelsLikeEntity(
    dayCelsius = ConvertUnit.temperature(
        value = day?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    dayFahrenheit = ConvertUnit.temperature(
        value = day?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    nightCelsius = ConvertUnit.temperature(
        value = night?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    nightFahrenheit = ConvertUnit.temperature(
        value = night?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    eveCelsius = ConvertUnit.temperature(
        value = eve?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    eveFahrenheit = ConvertUnit.temperature(
        value = eve?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    ),
    mornCelsius = ConvertUnit.temperature(
        value = morn?.toFloat(),
        to = TemperatureUnit.CELSIUS
    ),
    mornFahrenheit = ConvertUnit.temperature(
        value = morn?.toFloat(),
        to = TemperatureUnit.FAHRENHEIT
    )
)