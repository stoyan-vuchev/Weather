package com.stoyanvuchev.weather.data.remote.dto.combined

import com.google.gson.annotations.SerializedName

data class DailyWeather(
    @SerializedName("precipitation_sum")
    val precipitationSum: List<Double>,
    @SerializedName("sunrise")
    val sunrise: List<Long>,
    @SerializedName("sunset")
    val sunset: List<Long>,
    @SerializedName("temperature_2m_max")
    val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min")
    val temperature2mMin: List<Double>,
    @SerializedName("time")
    val time: List<Long>,
    @SerializedName("uv_index_max")
    val uvIndexMax: List<Double>,
    @SerializedName("weather_code")
    val weatherCode: List<Int>
)