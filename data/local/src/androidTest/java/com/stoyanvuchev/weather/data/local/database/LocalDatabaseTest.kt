/*
 * MIT License
 *
 * Copyright (c) 2025 Stoyan Vuchev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES, OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.stoyanvuchev.weather.data.local.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.stoyanvuchev.weather.data.local.database.entity.CurrentEntity
import com.stoyanvuchev.weather.data.local.database.entity.WeatherEntity
import com.stoyanvuchev.weather.data.local.database.typeconverter.WeatherEntityTypeConverter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDatabaseTest {

    private lateinit var db: LocalDatabase
    private lateinit var dao: LocalDatabaseDao

    @Before
    fun setUp() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LocalDatabase::class.java
        ).addTypeConverter(WeatherEntityTypeConverter()).build()
        dao = db.dao
    }

    @After
    fun tearDown() {
        if (this::db.isInitialized) {
            db.close()
        }
    }

    @Test
    fun upsert_and_getWeatherEntity_returnsInsertedData() = runTest {

        val entity = WeatherEntity(
            lat = "42.0",
            lon = "24.0",
            name = "",
            timestamp = "",
            timezone = "",
            timezoneOffset = 0L,
            current = CurrentEntity(),
            hourly = emptyList(),
            daily = emptyList()
        )

        dao.upsert(entity)
        val result = dao.getWeatherEntity("42.0", "24.0")

        assertThat(result).isNotNull()
        assertThat(result?.lat).isEqualTo("42.0")

    }

    @Test
    fun getAllWeatherEntities_returnsFlowWithData() = runTest {

        val entities = listOf(
            WeatherEntity(
                lat = "42.0",
                lon = "24.0",
                name = "",
                timestamp = "",
                timezone = "",
                timezoneOffset = 0L,
                current = CurrentEntity(),
                hourly = emptyList(),
                daily = emptyList()
            ),
            WeatherEntity(
                lat = "45.0",
                lon = "30.0",
                name = "",
                timestamp = "",
                timezone = "",
                timezoneOffset = 0L,
                current = CurrentEntity(),
                hourly = emptyList(),
                daily = emptyList()
            )
        )

        entities.forEach { dao.upsert(it) }
        val result = dao.getAllWeatherEntities()?.first()

        assertThat(result?.size).isEqualTo(2)

    }

    @Test
    fun upsert_replacesExistingEntity() = runTest {

        val entity = WeatherEntity(
            lat = "42.0",
            lon = "24.0",
            name = "",
            timestamp = "",
            timezone = "",
            timezoneOffset = 0L,
            current = CurrentEntity(),
            hourly = emptyList(),
            daily = emptyList()
        )

        dao.upsert(entity)

        val updated = entity.copy(timestamp = "1")
        dao.upsert(updated)

        val result = dao.getWeatherEntity("42.0", "24.0")
        assertThat(result?.timestamp).isEqualTo("1")

    }

    @Test
    fun deleteWeatherEntity_removesCorrectItem() = runTest {

        val entity1 = WeatherEntity(
            lat = "42.0",
            lon = "24.0",
            name = "",
            timestamp = "",
            timezone = "",
            timezoneOffset = 0L,
            current = CurrentEntity(),
            hourly = emptyList(),
            daily = emptyList()
        )

        val entity2 = WeatherEntity(
            lat = "45.0",
            lon = "30.0",
            name = "",
            timestamp = "",
            timezone = "",
            timezoneOffset = 0L,
            current = CurrentEntity(),
            hourly = emptyList(),
            daily = emptyList()
        )

        dao.upsert(entity1)
        dao.upsert(entity2)
        dao.deleteWeatherEntity("42.0", "24.0")

        val all = dao.getAllWeatherEntities()?.first()
        assertThat(all?.size).isEqualTo(1)
        assertThat(all?.first()?.lat).isEqualTo("45.0")

    }

    @Test
    fun deleteAllWeatherEntities_clearsDatabase() = runTest {

        val entity = WeatherEntity(
            lat = "42.0",
            lon = "24.0",
            name = "",
            timestamp = "",
            timezone = "",
            timezoneOffset = 0L,
            current = CurrentEntity(),
            hourly = emptyList(),
            daily = emptyList()
        )

        dao.upsert(entity)
        dao.deleteAllWeatherEntities()

        val all = dao.getAllWeatherEntities()?.first()
        assertThat(all?.isEmpty()).isEqualTo(true)

    }

}