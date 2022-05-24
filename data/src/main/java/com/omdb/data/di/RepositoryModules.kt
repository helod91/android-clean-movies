package com.omdb.data.di

import com.omdb.data.repositories.OmdbMovieRepository
import com.omdb.domain.repositories.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val repositoryModules = module {
    single<MovieRepository> {
        OmdbMovieRepository(get(), get())
    }
}