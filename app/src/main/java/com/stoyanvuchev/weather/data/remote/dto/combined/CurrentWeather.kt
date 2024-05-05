package com.stoyanvuchev.weather.data.remote.dto.combined

import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("apparent_temperature")
    val apparentTemperature: Double,
    @SerializedName("interval")
    val interval: Int,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("precipitation")
    val precipitation: Double,
    @SerializedName("pressure_msl")
    val pressureMsl: Double,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int,
    @SerializedName("temperature_2m")
    val temperature2m: Double,
    @SerializedName("time")
    val time: Long,
    @SerializedName("weather_code")
    val weatherCode: Int,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: Double,
    @SerializedName("wind_direction_10m")
    val windDirection10m: Double,
    @SerializedName("wind_gusts_10m")
    val windGusts10m: Double
)