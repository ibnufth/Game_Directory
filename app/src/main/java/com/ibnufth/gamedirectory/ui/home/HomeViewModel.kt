package com.ibnufth.gamedirectory.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.ibnufth.gamedirectory.core.domain.usecase.IGameUseCase

class HomeViewModel(gameUseCase: IGameUseCase) : ViewModel() {

    val games = gameUseCase.getAllGames().asLiveData()
}