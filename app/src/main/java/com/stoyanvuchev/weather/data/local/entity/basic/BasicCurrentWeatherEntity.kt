package com.stoyanvuchev.weather.data.local.entity.basic

import androidx.room.Entity

@Entity(
    tableName = "basic_current_weather_table",
    primaryKeys = ["latitude", "longitude"]
)
data class BasicCurrentWeatherEntity(
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