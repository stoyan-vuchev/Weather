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

package com.stoyanvuchev.weather.data.repository

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.stoyanvuchev.weather.data.local.database.LocalDatabase
import com.stoyanvuchev.weather.data.network.RemoteDataSource
import com.stoyanvuchev.weather.domain.etc.Resource
import com.stoyanvuchev.weather.domain.model.location.LocationType
import com.stoyanvuchev.weather.domain.other.unit.UnitConfiguration
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WeatherRepositoryImplTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var db: LocalDatabase
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        remoteDataSource = RemoteDataSource()
        db = LocalDatabase.instance(
            context = ApplicationProvider.getApplicationContext(),
            inMemory = true
        )
        repository = WeatherRepositoryImpl(remoteDataSource, db.dao)
    }

    @After
    fun tearDown() {
        if (this::db.isInitialized) {
            db.close()
        }
    }

    @Test
    fun getWeatherData_emitsLoadingAndSuccess_fromDaoAndRemote() = runTest {

        val lat = "42.0"
        val lon = "24.0"
        val name = "Test City"
        val type = LocationType.CURRENT
        val units = UnitConfiguration.METRIC

        repository.getWeatherData(
            System.currentTimeMillis(),
            lat,
            lon,
            name,
            type,
            units
        ).test {

            assertThat(awaitItem()).isInstanceOf(Resource.Loading::class.java)

            val success = awaitItem() as Resource.Success
            assertThat(success.data?.lat).isEqualTo(lat)
            assertThat(success.data?.lon).isEqualTo(lon)
            assertThat(success.data?.name).isEqualTo(name)
            assertThat(success.data?.type).isEqualTo(type)

            awaitComplete()

        }

        repository.getWeatherData(
            System.currentTimeMillis(),
            lat,
            lon,
            name,
            type,
            units
        ).test {

            val loading = awaitItem() as Resource.Loading
            val success = awaitItem() as Resource.Success

            assertThat(loading).isInstanceOf(Resource.Loading::class.java)
            assertThat(success.data?.lat).isEqualTo(lat)
            assertThat(success.data?.name).isEqualTo(name)

            awaitComplete()

        }

    }

}