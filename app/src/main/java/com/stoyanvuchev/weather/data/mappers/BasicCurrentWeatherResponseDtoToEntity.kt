package com.stoyanvuchev.weather.data.mappers

import com.stoyanvuchev.weather.data.local.entity.basic.BasicCurrentWeatherEntity
import com.stoyanvuchev.weather.data.remote.dto.basic.BasicCurrentWeatherResponseDto

fun BasicCurrentWeatherResponseDto.toEntity() = BasicCurrentWeatherEntity(
    interval = current.interval,
    isDay = current.isDay == 1,
    temperature2m = current.temperature2m,
    time = current.time,
    weatherCode = current.weatherCode,
    elevation = elevation,
    generationtimeMs = generationtimeMs,
    latitude = latitude,
    longitude = longitude,
    timezone = timezone,
    timezoneAbbreviation = timezoneAbbreviation,
    utcOffsetSeconds = utcOffsetSeconds
)