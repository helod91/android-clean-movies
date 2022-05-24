package com.omdb.cleanmovies.ui.search

import com.omdb.cleanmovies.common.ViewState
import com.omdb.domain.models.Error
import com.omdb.domain.models.MovieSearchModel

sealed class SearchMoviesState : ViewState {
    object Loading : SearchMoviesState()
    object ResultEmpty : SearchMoviesState()
    data class ResultMovies(val data: List<MovieSearchModel>): SearchMoviesState()
    data class Exception(val error: Error?): SearchMoviesState()
}