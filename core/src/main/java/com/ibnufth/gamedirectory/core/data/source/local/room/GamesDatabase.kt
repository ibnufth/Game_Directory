package com.ibnufth.gamedirectory.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibnufth.gamedirectory.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}