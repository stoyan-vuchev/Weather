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

package com.stoyanvuchev.weather.data.network

import com.stoyanvuchev.weather.data.network.dto.WeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class RemoteDataSource(
    private val client: HttpClient = RemoteDataSourceHttpClient.instance()
) {

    suspend fun getWeatherData(
        lat: String,
        lon: String
    ): WeatherResponseDto {
        return client.get(BuildConfig.HOST_URL + BuildConfig.HOME_GET_REQUEST) {
            url {
                parameters.append("lat", lat)
                parameters.append("lon", lon)
                parameters.append("units", "metric")
                parameters.append("lang", "en")
                parameters.append("exclude", BuildConfig.EXCLUDE_PARAMETERS)
                parameters.append("appid", BuildConfig.API_KEY)
            }
        }.body()
    }

}