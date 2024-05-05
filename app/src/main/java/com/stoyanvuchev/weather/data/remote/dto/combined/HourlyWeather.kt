package com.stoyanvuchev.weather.data.remote.dto.combined

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("apparent_temperature")
    val apparentTemperature: List<Double>,
    @SerializedName("is_day")
    val isDay: List<Int>,
    @SerializedName("precipitation")
    val precipitation: List<Double>,
    @SerializedName("pressure_msl")
    val pressureMsl: List<Double>,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: List<Int>,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("uv_index")
    val uvIndex: List<Double>,
    @SerializedName("visibility")
    val visibility: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>,
    @SerializedName("wind_direction_10m")
    val windDirection10m: List<Double>,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: List<Double>,
    @SerializedName("wind_gusts_10m")
    val windGusts10m: List<Double>
)