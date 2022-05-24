package com.omdb.cleanmovies.ui.details

import com.omdb.cleanmovies.common.ViewAction

sealed class MovieDetailsAction : ViewAction {
    data class MovieDetails(val movieId: String?): MovieDetailsAction()
}