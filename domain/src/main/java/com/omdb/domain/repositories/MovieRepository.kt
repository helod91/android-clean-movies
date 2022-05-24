package com.omdb.domain.repositories

import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.MovieSearchResultModel
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun searchMovies(title: String?): MovieSearchResultModel

    suspend fun getMovieDetails(id: String?): MovieDetailsModel
}