package com.stoyanvuchev.weather.data.local

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.stoyanvuchev.weather.data.local.entity.basic.BasicCurrentWeatherEntity
import com.stoyanvuchev.weather.data.local.entity.combined.CombinedWeatherEntity
import com.stoyanvuchev.weather.data.remote.dto.combined.CurrentWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.DailyWeather
import com.stoyanvuchev.weather.data.remote.dto.combined.HourlyWeather
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDatabaseTest {

    private lateinit var database: LocalDatabase

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = LocalDatabase.createInstance(context.applicationContext, true)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun write_and_read_basic_current_weather_entity() = runTest {

        val expected = basicCurrentWeatherEntity

        database.dao.upsertBasicCurrentWeatherData(expected)

        val actual = database.dao.getBasicCurrentWeatherData(LAT, LON)

        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun delete_basic_current_weather_entity() = runTest {

        val expected = basicCurrentWeatherEntity

        database.dao.upsertBasicCurrentWeatherData(expected)
        database.dao.deleteBasicCurrentWeatherData(LAT, LON)

        val actual = database.dao.getBasicCurrentWeatherData(LAT, LON)

        assertThat(actual).isNull()

    }

    @Test
    fun write_and_read_combined_weather_entity() = runTest {

        val expected = combinedWeatherEntity

        database.dao.upsertCombinedWeatherData(expected)

        val actual = database.dao.getCombinedWeatherData(LAT, LON)

        assertThat(actual).isEqualTo(expected)

    }

    @Test
    fun delete_combined_weather_entity() = runTest {

        val expected = combinedWeatherEntity

        database.dao.upsertCombinedWeatherData(expected)
        database.dao.deleteCombinedWeatherData(LAT, LON)

        val actual = database.dao.getCombinedWeatherData(LAT, LON)

        assertThat(actual).isNull()

    }

    @Test
    fun observe_combined_weather_entity() = runTest {

        val expected = listOf(combinedWeatherEntity)

        database.dao.upsertCombinedWeatherData(combinedWeatherEntity)
        database.dao.deleteBasicCurrentWeatherData(LAT, LON)

        var actual = emptyList<CombinedWeatherEntity>()

        database.dao.getAllCombinedWeatherData().test {
            actual = awaitItem()
        }

        assertThat(actual).isEqualTo(expected)

    }

    companion object {

        private const val LAT = 42.0
        private const val LON = 24.0

        val basicCurrentWeatherEntity = BasicCurrentWeatherEntity(
            interval = 0,
            isDay = true,
            temperature2m = 23.0,
            time = 10L,
            weatherCode = 1,
            elevation = 10.0,
            generationtimeMs = 1.0,
            latitude = LAT,
            longitude = LON,
            timezone = "Europe/Sofia",
            timezoneAbbreviation = "EET",
            utcOffsetSeconds = 1
        )

        private val combinedWeatherEntity = CombinedWeatherEntity(
            interval = 0,
            isDay = true,
            temperature2m = 23.0,
            time = 10L,
            weatherCode = 1,
            elevation = 10.0,
            generationtimeMs = 1.0,
            latitude = LAT,
            longitude = LON,
            timezone = "Europe/Sofia",
            timezoneAbbreviation = "EET",
            utcOffsetSeconds = 1,
            current = CurrentWeather(
                apparentTemperature = 23.0,
                interval = 1,
                isDay = 1,
                precipitation = 0.0,
                pressureMsl = 1000.0,
                relativeHumidity2m = 30,
                temperature2m = 23.0,
                time = 0L,
                weatherCode = 1,
                windSpeed10m = 10.0,
                windDirection10m = 10.0,
                windGusts10m = 10.0
            ),
            hourly = HourlyWeather(
                apparentTemperature = emptyList(),
                isDay = emptyList(),
                precipitation = emptyList(),
                pressureMsl = emptyList(),
                relativeHumidity2m = emptyList(),
                temperature2m = emptyList(),
                time = emptyList(),
                uvIndex = emptyList(),
                visibility = emptyList(),
                weatherCode = emptyList(),
                windDirection10m = emptyList(),
                windSpeed10m = emptyList(),
                windGusts10m = emptyList()
            ),
            daily = DailyWeather(
                precipitationSum = emptyList(),
                sunrise = emptyList(),
                sunset = emptyList(),
                temperature2mMax = emptyList(),
                temperature2mMin = emptyList(),
                time = emptyList(),
                uvIndexMax = emptyList(),
                weatherCode = emptyList()
            )
        )

    }

}