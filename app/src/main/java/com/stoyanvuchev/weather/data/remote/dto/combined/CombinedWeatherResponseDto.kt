package com.stoyanvuchev.weather.data.remote.dto.combined

import com.google.gson.annotations.SerializedName

data class CombinedWeatherResponseDto(
    @SerializedName("current")
    val current: CurrentWeather,
    @SerializedName("daily")
    val daily: DailyWeather,
    @SerializedName("elevation")
    val elevation: Double,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    @SerializedName("hourly")
    val hourly: HourlyWeather,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
    @SerializedName("timezone")
    val timeZone: String,
    @SerializedName("timezone_abbreviation")
    val timeZoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)