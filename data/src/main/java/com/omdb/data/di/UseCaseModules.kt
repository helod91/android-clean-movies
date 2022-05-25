package com.omdb.data.di

import com.omdb.domain.usecases.GetMovieDetails
import com.omdb.domain.usecases.SearchMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val useCaseModules = module {
    factory {
        SearchMovies(get(), get())
    }

    factory {
        GetMovieDetails(get(), get())
    }
}