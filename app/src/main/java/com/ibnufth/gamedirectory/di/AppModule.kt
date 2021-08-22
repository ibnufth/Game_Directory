package com.ibnufth.gamedirectory.di

import com.ibnufth.gamedirectory.core.domain.usecase.GameInteractor
import com.ibnufth.gamedirectory.core.domain.usecase.IGameUseCase
import com.ibnufth.gamedirectory.ui.detail.DetailViewModel
import com.ibnufth.gamedirectory.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<IGameUseCase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}