package com.ibnufth.gamedirectory.core.domain.usecase


import com.ibnufth.gamedirectory.core.data.Resource
import com.ibnufth.gamedirectory.core.domain.model.Games
import com.ibnufth.gamedirectory.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : IGameUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> =
        gameRepository.getAllGames()

    override fun getFavoriteGame(): Flow<List<Games>> =
        gameRepository.getFavoriteGame()

    override suspend fun setFavoriteGame(game: Games, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)

    override fun getDetailGame(id: Int): Flow<Games> =
        gameRepository.getDetailGame(id)

}