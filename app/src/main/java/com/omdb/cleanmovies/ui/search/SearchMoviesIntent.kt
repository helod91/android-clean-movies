package com.omdb.cleanmovies.ui.search

import com.omdb.cleanmovies.common.ViewIntent

sealed class SearchMoviesIntent : ViewIntent {
    data class SearchMovies(val query: String): SearchMoviesIntent()
}