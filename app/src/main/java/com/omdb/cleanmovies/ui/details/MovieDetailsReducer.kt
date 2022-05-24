package com.omdb.cleanmovies.ui.details

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.Status

fun Data<MovieDetailsModel>.reduce(): MovieDetailsState {
    return when (responseType) {
        Status.SUCCESSFUL -> data?.let { MovieDetailsState.ResultMovieDetails(it) } ?: MovieDetailsState.ResultEmpty
        Status.ERROR -> MovieDetailsState.GenericError
        Status.LOADING -> MovieDetailsState.Loading
    }
}