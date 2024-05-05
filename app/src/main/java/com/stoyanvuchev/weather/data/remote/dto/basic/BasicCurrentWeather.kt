package com.stoyanvuchev.weather.data.remote.dto.basic

import com.google.gson.annotations.SerializedName

data class BasicCurrentWeather(
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("temperature_2m")
    val temperature2m: Double,
    @SerializedName("time")
    val time: Long,
    @SerializedName("weather_code")
    val weatherCode: Int
)