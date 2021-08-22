package com.ibnufth.gamedirectory.core.data

import android.util.Log
import com.ibnufth.gamedirectory.core.data.source.local.LocalDataSource
import com.ibnufth.gamedirectory.core.data.source.remote.RemoteDataSource
import com.ibnufth.gamedirectory.core.data.source.remote.api.ApiResponse
import com.ibnufth.gamedirectory.core.data.source.remote.response.GamesResponse
import com.ibnufth.gamedirectory.core.domain.model.Games
import com.ibnufth.gamedirectory.core.domain.repository.IGameRepository
import com.ibnufth.gamedirectory.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IGameRepository {
    override fun getAllGames(): Flow<Resource<List<Games>>> =
        object : NetworkBoundResource<List<Games>, List<GamesResponse>>() {
            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Games>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GamesResponse>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GamesResponse>) {
                val gameList = DataMapper.mapResponseToEntities(data)
                Log.d(TAG, "saveCallResult: repo data $gameList")
                localDataSource.insertGame(gameList)
            }
        }.asFlow()


    override fun getFavoriteGame(): Flow<List<Games>> =
        localDataSource.getFavoriteGame().map { DataMapper.mapEntitiesToDomain(it) }

    override suspend fun setFavoriteGame(game: Games, state: Boolean) = coroutineScope {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        withContext(Dispatchers.Default) {
            localDataSource.setFavoriteGame(
                gameEntity,
                state
            )
        }
    }

    override fun getDetailGame(id: Int): Flow<Games> {
        return localDataSource.getGameById(id).map { DataMapper.mapEntityToDomain(it) }
    }
}