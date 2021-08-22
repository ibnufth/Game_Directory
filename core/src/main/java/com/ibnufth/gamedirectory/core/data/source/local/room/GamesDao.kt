package com.ibnufth.gamedirectory.core.data.source.local.room

import androidx.room.*
import com.ibnufth.gamedirectory.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.selects.select

@Dao
interface GamesDao {
    @Query("select * from game")
    fun getAllGames(): Flow<List<GamesEntity>>

    @Query("select * from game where favorite = 1")
    fun getFavoriteGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GamesEntity>)

    @Update
    fun updateGame(game: GamesEntity)

    @Query("select * from game where id = :id")
    fun getGameById(id: Int): Flow<GamesEntity>
}