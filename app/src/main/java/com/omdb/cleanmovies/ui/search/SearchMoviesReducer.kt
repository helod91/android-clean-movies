package com.omdb.cleanmovies.ui.search

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.models.Status

fun Data<MovieSearchResultModel>.reduce(): SearchMoviesState {
   return when (responseType) {
       Status.SUCCESSFUL -> data?.movies?.let {
           if (it.isEmpty()) SearchMoviesState.ResultEmpty else SearchMoviesState.ResultMovies(it)
       } ?: SearchMoviesState.ResultEmpty
       Status.ERROR -> SearchMoviesState.Exception(error)
       Status.LOADING -> SearchMoviesState.Loading
   }
}