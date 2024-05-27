package com.stoyanvuchev.weather.data.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.stoyanvuchev.weather.data.remote.dto.combined.CurrentWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.DailyWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.HourlyWeather

class LocalDatabaseTypeConverters {

    @TypeConverter
    fun currentWeatherToJson(src: CurrentWeather): String {
        val type = object : TypeToken<CurrentWeather>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun currentWeatherFromJson(src: String): CurrentWeather {
        val type = object : TypeToken<CurrentWeather>() {}.type
        return Gson().fromJson(src, type)
    }

    @TypeConverter
    fun hourlyWeatherToJson(src: HourlyWeather): String {
        val type = object : TypeToken<HourlyWeather>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun hourlyWeatherFromJson(src: String): HourlyWeather {
        val type = object : TypeToken<HourlyWeather>() {}.type
        return Gson().fromJson(src, type)
    }

    @TypeConverter
    fun dailyWeatherToJson(src: DailyWeather): String {
        val type = object : TypeToken<DailyWeather>() {}.type
        return Gson().toJson(src, type)
    }

    @TypeConverter
    fun dailyWeatherFromJson(src: String): DailyWeather {
        val type = object : TypeToken<DailyWeather>() {}.type
        return Gson().fromJson(src, type)
    }

}