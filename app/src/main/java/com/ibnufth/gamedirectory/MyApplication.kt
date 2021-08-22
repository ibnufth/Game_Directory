package com.ibnufth.gamedirectory

import android.app.Application
import com.ibnufth.gamedirectory.core.di.databaseModule
import com.ibnufth.gamedirectory.core.di.networkModule
import com.ibnufth.gamedirectory.core.di.repositoryModule
import com.ibnufth.gamedirectory.di.useCaseModule
import com.ibnufth.gamedirectory.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}