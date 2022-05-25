package com.omdb.cleanmovies

import android.app.Application
import com.omdb.cleanmovies.di.viewModelModules
import com.omdb.data.di.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class CleanMoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CleanMoviesApplication)
            modules(listOf(
                apiModules,
                mapperModules,
                networkModules,
                repositoryModules,
                useCaseModules,
                viewModelModules
            ))
        }
    }
}