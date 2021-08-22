package com.ibnufth.gamedirectory.core.data.source.local

import com.ibnufth.gamedirectory.core.data.source.local.entity.GamesEntity
import com.ibnufth.gamedirectory.core.data.source.local.room.GamesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gamesDao: GamesDao) {

    fun getAllGames(): Flow<List<GamesEntity>> = gamesDao.getAllGames()

    fun getFavoriteGame(): Flow<List<GamesEntity>> = gamesDao.getFavoriteGames()

    suspend fun insertGame(gamesList: List<GamesEntity>) = gamesDao.insertGame(gamesList)

    fun setFavoriteGame(games: GamesEntity, newState: Boolean) {
        games.favorite = newState
        gamesDao.updateGame(games)
    }

    fun getGameById(id: Int): Flow<GamesEntity> = gamesDao.getGameById(id)
}