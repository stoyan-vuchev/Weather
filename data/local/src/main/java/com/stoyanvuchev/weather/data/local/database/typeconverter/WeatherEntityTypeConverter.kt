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

package com.stoyanvuchev.weather.data.local.database.typeconverter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.stoyanvuchev.weather.data.local.database.entity.CurrentEntity
import com.stoyanvuchev.weather.data.local.database.entity.DailyEntity
import com.stoyanvuchev.weather.data.local.database.entity.FeelsLikeEntity
import com.stoyanvuchev.weather.data.local.database.entity.HourlyEntity
import com.stoyanvuchev.weather.data.local.database.entity.TempEntity
import com.stoyanvuchev.weather.domain.model.weather.RainModel
import com.stoyanvuchev.weather.domain.model.weather.SnowModel
import kotlinx.serialization.json.Json

@ProvidedTypeConverter
class WeatherEntityTypeConverter {

    private val json = Json { encodeDefaults = true }

    // Current

    @TypeConverter
    fun fromJsonToCurrentEntity(value: String): CurrentEntity {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromCurrentEntityToJson(value: CurrentEntity): String {
        return json.encodeToString(value)
    }

    // Hourly

    @TypeConverter
    fun fromJsonToHourlyEntity(value: String): HourlyEntity {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromHourlyEntityToJson(value: HourlyEntity): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun fromJsonToHourlyEntityList(value: String): List<HourlyEntity> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromHourlyEntityListToJson(value: List<HourlyEntity>): String {
        return json.encodeToString(value)
    }

    // Daily

    @TypeConverter
    fun fromJsonToDailyEntity(value: String): DailyEntity {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromDailyEntityToJson(value: DailyEntity): String {
        return json.encodeToString(value)
    }

    @TypeConverter
    fun fromJsonToDailyEntityList(value: String): List<DailyEntity> {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromDailyEntityListToJson(value: List<DailyEntity>): String {
        return json.encodeToString(value)
    }

    // Temperature

    @TypeConverter
    fun fromJsonToTempEntity(value: String): TempEntity {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromTempEntityToJson(value: TempEntity): String {
        return json.encodeToString(value)
    }

    // Feels like

    @TypeConverter
    fun fromJsonToFeelsLikeEntity(value: String): FeelsLikeEntity {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromFeelsLikeEntityToJson(value: FeelsLikeEntity): String {
        return json.encodeToString(value)
    }

    // Rain Model

    @TypeConverter
    fun fromJsonToRainModel(value: String): RainModel {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromRainModelToJson(value: RainModel): String {
        return json.encodeToString(value)
    }

    // Rain Model

    @TypeConverter
    fun fromJsonToSnowModel(value: String): SnowModel {
        return json.decodeFromString(value)
    }

    @TypeConverter
    fun fromSnowModelToJson(value: SnowModel): String {
        return json.encodeToString(value)
    }

}