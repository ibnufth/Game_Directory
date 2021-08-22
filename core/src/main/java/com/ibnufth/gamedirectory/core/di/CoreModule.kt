package com.ibnufth.gamedirectory.core.di

import androidx.room.Room
import com.ibnufth.gamedirectory.core.data.GameRepository
import com.ibnufth.gamedirectory.core.data.source.local.LocalDataSource
import com.ibnufth.gamedirectory.core.data.source.local.room.GamesDatabase
import com.ibnufth.gamedirectory.core.data.source.remote.RemoteDataSource
import com.ibnufth.gamedirectory.core.data.source.remote.api.ApiService
import com.ibnufth.gamedirectory.core.domain.repository.IGameRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GamesDatabase>().gamesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GamesDatabase::class.java, "Games.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IGameRepository> {
        GameRepository(
            get(),
            get()
        )
    }
}