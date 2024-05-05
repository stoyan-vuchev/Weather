package com.stoyanvuchev.weather.data.remote

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class RemoteDataSourceTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private val latitude = 42.0
    private val longitude = 24.0

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource.createInstance()
    }

    @Test
    fun `Fetch basic current weather data`() = runTest {

        val response = remoteDataSource.fetchBasicCurrentWeatherData(
            latitude = latitude,
            longitude = longitude
        )

        println("Fetch basic current weather data: ${response.body()}")

        val lat = response.body()?.latitude
        val lon = response.body()?.longitude

        assertThat(lat).isNotNull().isEqualTo(latitude)
        assertThat(lon).isNotNull().isEqualTo(longitude)

    }

    @Test
    fun `Fetch combined weather data`() = runTest {

        val response = remoteDataSource.fetchCombinedWeatherData(
            latitude = latitude,
            longitude = longitude
        )

        println("Fetch combined current weather data: ${response.body()}")

        val lat = response.body()?.latitude
        val lon = response.body()?.longitude

        assertThat(lat).isNotNull().isEqualTo(latitude)
        assertThat(lon).isNotNull().isEqualTo(longitude)

    }

}