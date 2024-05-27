package com.stoyanvuchev.weather.data.local.entity.combined

import androidx.room.Entity
import com.stoyanvuchev.weather.data.remote.dto.combined.CurrentWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.DailyWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.HourlyWeather

@Entity(
    tableName = "combined_weather_table",
    primaryKeys = ["latitude", "longitude"]
)
data class CombinedWeatherEntity(
    val current: CurrentWeather,
    val daily: DailyWeather,
    val hourly: HourlyWeather,
    val interval: Int,
    val isDay: Boolean,
    val temperature2m: Double,
    val time: Long,
    val weatherCode: Int,
    val elevation: Double,
    val generationtimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezoneAbbreviation: String,
    val utcOffsetSeconds: Int
)