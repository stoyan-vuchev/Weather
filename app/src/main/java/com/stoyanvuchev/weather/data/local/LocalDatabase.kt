package com.stoyanvuchev.weather.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.stoyanvuchev.weather.data.local.converters.LocalDatabaseTypeConverters
import com.stoyanvuchev.weather.data.local.entity.basic.BasicCurrentWeatherEntity
import com.stoyanvuchev.weather.data.local.entity.combined.CombinedWeatherEntity

@Database(
    entities = [BasicCurrentWeatherEntity::class, CombinedWeatherEntity::class],
    version = 1,
    exportSchema = true,
    autoMigrations = []
)
@TypeConverters(LocalDatabaseTypeConverters::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract val dao: LocalDatabaseDao

    companion object {

        fun createInstance(
            context: Context,
            inMemory: Boolean = false
        ) = if (!inMemory) {

            Room.databaseBuilder(
                context = context,
                klass = LocalDatabase::class.java,
                name = "weather_db"
            ).build()

        } else {

            Room.inMemoryDatabaseBuilder(
                context = context,
                klass = LocalDatabase::class.java
            ).build()

        }

    }

}