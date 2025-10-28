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

import com.stoyanvuchev.weather.data.local.database.LocalDatabaseDao
import com.stoyanvuchev.weather.data.network.RemoteDataSource
import com.stoyanvuchev.weather.data.network.dto.WeatherResponseDto
import com.stoyanvuchev.weather.data.repository.mappers.toEntity
import com.stoyanvuchev.weather.data.repository.mappers.toModel
import com.stoyanvuchev.weather.domain.etc.Resource
import com.stoyanvuchev.weather.domain.model.location.LocationType
import com.stoyanvuchev.weather.domain.model.weather.WeatherModel
import com.stoyanvuchev.weather.domain.other.unit.UnitConfiguration
import com.stoyanvuchev.weather.domain.repository.WeatherRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.time.Duration.Companion.minutes

class WeatherRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val dao: LocalDatabaseDao
) : WeatherRepository {

    override suspend fun getWeatherData(
        currentTimeMillis: Long,
        lat: String,
        lon: String,
        name: String,
        type: LocationType,
        unitConfiguration: UnitConfiguration
    ): Flow<Resource<WeatherModel>> = callbackFlow {

        trySend(Resource.Loading())

        try {

            val data = dao.getWeatherEntity(lat, lon)
            if (data != null) {

                val tenMinutesAgo = currentTimeMillis - 10.minutes.inWholeMilliseconds
                if (tenMinutesAgo < data.timestamp.toLong()) {

                    val newData = data.toModel(unitConfiguration)
                    trySend(Resource.Success(newData))

                } else {

                    val result = getRemoteData(lat, lon)
                    if (result is Resource.Success) {

                        val dto = result.data
                        if (dto != null) {

                            dao.upsert(dto.toEntity().copy(name = name))
                            delay(250)

                            val newData = dao.getWeatherEntity(lat, lon)
                                ?.toModel(unitConfiguration)
                                ?.copy(name = name, type = type)

                            if (newData != null) {
                                trySend(Resource.Success(newData))
                            } else {
                                trySend(Resource.Error())
                            }

                        } else {
                            trySend(Resource.Error(result.error)); close()
                        }

                    } else {
                        trySend(Resource.Error(result.error)); close()
                    }

                }

            } else {

                val result = getRemoteData(lat, lon)
                if (result is Resource.Success) {

                    val dto = result.data
                    if (dto != null) {

                        dao.upsert(dto.toEntity().copy(name = name))
                        delay(250)

                        val newData = dao.getWeatherEntity(lat, lon)
                            ?.toModel(unitConfiguration)
                            ?.copy(name = name, type = type)

                        if (newData != null) {
                            trySend(Resource.Success(newData))
                        } else {
                            trySend(Resource.Error())
                        }

                    } else {
                        trySend(Resource.Error(result.error)); close()
                    }

                } else {
                    trySend(Resource.Error(result.error)); close()
                }

            }

            close()

        } catch (e: Exception) {
            e.printStackTrace()
            trySend(Resource.Error(e.message)); close()
        }

        awaitClose {}

    }

    private suspend fun getRemoteData(
        lat: String,
        lon: String
    ): Resource<WeatherResponseDto> {
        return try {
            val response = remoteDataSource.getWeatherData(lat, lon)
            Resource.Success(response.copy(lat = lat.toDouble(), lon = lon.toDouble()))
        } catch (e: Exception) {
            Resource.Error(e.message)
        }
    }

}