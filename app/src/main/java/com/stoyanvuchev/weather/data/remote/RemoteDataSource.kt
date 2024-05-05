package com.stoyanvuchev.weather.data.remote

import com.stoyanvuchev.weather.BuildConfig
import com.stoyanvuchev.weather.data.remote.dto.basic.BasicCurrentWeatherResponseDto
import com.stoyanvuchev.weather.data.remote.dto.combined.CombinedWeatherResponseDto
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteDataSource {

    @GET(BuildConfig.FORECAST_REQUEST)
    suspend fun fetchBasicCurrentWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("temperature_unit") temperatureUnit: String = "celsius",
        @Query("current") current: String = BuildConfig.CURRENT_INFO_BASIC,
        @Query("timezone") timezone: String = "auto",
        @Query("timeformat") timeFormat: String = "unixtime"
    ): Response<BasicCurrentWeatherResponseDto>

    @GET(BuildConfig.FORECAST_REQUEST)
    suspend fun fetchCombinedWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("temperature_unit") temperatureUnit: String = "celsius",
        @Query("wind_speed_unit") windSpeedUnit: String = "kmh",
        @Query("precipitation_unit") precipitationUnit: String = "mm",
        @Query("current") current: String = BuildConfig.CURRENT_INFO,
        @Query("hourly") hourly: String = BuildConfig.HOURLY_INFO,
        @Query("daily") daily: String = BuildConfig.DAILY_INFO,
        @Query("timezone") timezone: String = "auto",
        @Query("timeformat") timeFormat: String = "unixtime"
    ): Response<CombinedWeatherResponseDto>

    companion object {

        fun createInstance(): RemoteDataSource = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteDataSource::class.java)

    }

}