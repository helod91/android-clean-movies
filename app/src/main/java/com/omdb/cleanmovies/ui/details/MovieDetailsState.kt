package com.omdb.cleanmovies.ui.details

import com.omdb.cleanmovies.common.ViewState
import com.omdb.domain.models.MovieDetailsModel

sealed class MovieDetailsState : ViewState {
    object Loading : MovieDetailsState()
    object ResultEmpty : MovieDetailsState()
    object GenericError : MovieDetailsState()
    object NoInternetConnection : MovieDetailsState()
    data class ResultMovieDetails(val data: MovieDetailsModel) : MovieDetailsState()
}