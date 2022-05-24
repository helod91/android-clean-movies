package com.omdb.cleanmovies.ui.search

import com.omdb.cleanmovies.common.ViewAction

sealed class SearchMoviesAction : ViewAction {
    data class SearchMovies(val query: String) : SearchMoviesAction()
}