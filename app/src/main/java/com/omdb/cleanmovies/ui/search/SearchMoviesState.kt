package com.omdb.cleanmovies.ui.search

import com.omdb.cleanmovies.common.ViewState
import com.omdb.domain.models.error.DomainException
import com.omdb.domain.models.MovieSearchModel

sealed class SearchMoviesState : ViewState {
    object Loading : SearchMoviesState()
    object ResultEmpty : SearchMoviesState()
    object RandomError: SearchMoviesState()
    object UnhandledError: SearchMoviesState()
    object NoInternetConnection: SearchMoviesState()
    object NoInputProvided: SearchMoviesState()
    data class ResultMovies(val data: List<MovieSearchModel>): SearchMoviesState()
}