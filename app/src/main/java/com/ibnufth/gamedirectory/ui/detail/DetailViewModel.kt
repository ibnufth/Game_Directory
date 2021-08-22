package com.ibnufth.gamedirectory.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibnufth.gamedirectory.core.domain.model.Games
import com.ibnufth.gamedirectory.core.domain.usecase.IGameUseCase

class DetailViewModel(private val gameUseCase: IGameUseCase) : ViewModel() {

    suspend fun setFavorite(game: Games, newState: Boolean) {
        gameUseCase.setFavoriteGame(game, newState)
    }
    fun getDetailGame(id :Int) : LiveData<Games> {
        return gameUseCase.getDetailGame(id).asLiveData()
    }
}