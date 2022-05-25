package com.omdb.data.di

import com.omdb.data.repositories.DeviceNetworkInfoProvider
import com.omdb.data.repositories.OmdbMovieRepository
import com.omdb.domain.repositories.MovieRepository
import com.omdb.domain.repositories.NetworkInfoProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val repositoryModules = module {
    single<MovieRepository> {
        OmdbMovieRepository(get(), get())
    }

    single<NetworkInfoProvider> {
        DeviceNetworkInfoProvider(androidContext())
    }
}