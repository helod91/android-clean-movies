package com.omdb.cleanmovies.ui.details

import com.omdb.cleanmovies.common.ViewIntent

sealed class MovieDetailsIntent : ViewIntent {
    data class LoadMovieDetails(val movieId: String?) : MovieDetailsIntent()
}