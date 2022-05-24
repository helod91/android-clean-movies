package com.omdb.cleanmovies.di

import com.omdb.cleanmovies.ui.details.MovieDetailsViewModel
import com.omdb.cleanmovies.ui.search.SearchMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@ExperimentalCoroutinesApi
val viewModelModules = module {
    viewModel { SearchMoviesViewModel(get()) }
    viewModel { MovieDetailsViewModel(get()) }
}