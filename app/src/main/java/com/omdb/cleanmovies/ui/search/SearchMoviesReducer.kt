package com.omdb.cleanmovies.ui.search

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.models.Status
import com.omdb.domain.models.error.DomainErrorCode

fun Data<MovieSearchResultModel>.reduce(): SearchMoviesState {
   return when (responseType) {
       Status.SUCCESSFUL -> data?.movies?.let {
           if (it.isEmpty()) SearchMoviesState.ResultEmpty else SearchMoviesState.ResultMovies(it)
       } ?: SearchMoviesState.ResultEmpty
       Status.ERROR -> {
           when (domainException?.code) {
               DomainErrorCode.TEST_ERROR -> SearchMoviesState.RandomError
               DomainErrorCode.NO_INTERNET_CONNECTION -> SearchMoviesState.NoInternetConnection
               DomainErrorCode.NO_INPUT -> SearchMoviesState.NoInputProvided
               else -> SearchMoviesState.UnhandledError
           }
       }
       Status.LOADING -> SearchMoviesState.Loading
   }
}