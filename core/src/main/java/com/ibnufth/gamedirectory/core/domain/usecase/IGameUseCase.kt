package com.ibnufth.gamedirectory.core.domain.usecase

import com.ibnufth.gamedirectory.core.data.Resource
import com.ibnufth.gamedirectory.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IGameUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>

    fun getFavoriteGame(): Flow<List<Games>>

    suspend fun setFavoriteGame(game: Games, state: Boolean)

    fun getDetailGame(id : Int):Flow<Games>

}