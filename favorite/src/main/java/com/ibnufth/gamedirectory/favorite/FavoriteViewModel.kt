package com.ibnufth.gamedirectory.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibnufth.gamedirectory.core.domain.usecase.IGameUseCase

class FavoriteViewModel(gameUseCase: IGameUseCase) : ViewModel() {
    val favoriteGame = gameUseCase.getFavoriteGame().asLiveData()
}