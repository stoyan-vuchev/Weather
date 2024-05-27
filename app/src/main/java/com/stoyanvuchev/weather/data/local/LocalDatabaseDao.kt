package com.stoyanvuchev.weather.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.stoyanvuchev.weather.data.local.entity.basic.BasicCurrentWeatherEntity
import com.stoyanvuchev.weather.data.local.entity.combined.CombinedWeatherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDatabaseDao {

    // Basic

    @Upsert
    suspend fun upsertBasicCurrentWeatherData(entity: BasicCurrentWeatherEntity)

    @Query(
        "SELECT * FROM basic_current_weather_table " +
                "WHERE latitude = :latitude AND longitude = :longitude LIMIT 1"
    )
    suspend fun getBasicCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): BasicCurrentWeatherEntity?

    @Query(
        "DELETE FROM basic_current_weather_table " +
                "WHERE latitude = :latitude AND longitude = :longitude"
    )
    suspend fun deleteBasicCurrentWeatherData(
        latitude: Double,
        longitude: Double
    )

    // Combined

    @Upsert
    suspend fun upsertCombinedWeatherData(entity: CombinedWeatherEntity)

    @Query("SELECT * FROM combined_weather_table")
    fun getAllCombinedWeatherData(): Flow<List<CombinedWeatherEntity>>

    @Query(
        "SELECT * FROM combined_weather_table " +
                "WHERE latitude = :latitude AND longitude = :longitude LIMIT 1"
    )
    suspend fun getCombinedWeatherData(
        latitude: Double,
        longitude: Double
    ): CombinedWeatherEntity?

    @Query(
        "DELETE FROM combined_weather_table " +
                "WHERE latitude = :latitude AND longitude = :longitude"
    )
    suspend fun deleteCombinedWeatherData(
        latitude: Double,
        longitude: Double
    )

}