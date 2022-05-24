package com.omdb.data.repositories

import com.omdb.data.apiservice.ApiService
import com.omdb.data.mappers.MovieMapper
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.repositories.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class OmdbMovieRepository(
    private val apiService: ApiService,
    private val movieMapper: MovieMapper
) : MovieRepository {

    override suspend fun searchMovies(title: String?): MovieSearchResultModel {
        val apiResult = apiService.searchMovies(title)
        return movieMapper.toMovieSearchResult(apiResult)
    }

    override suspend fun getMovieDetails(id: String?): MovieDetailsModel {
        val apiResult = apiService.getMovieDetails(id)
        return movieMapper.toMovieDetails(apiResult)
    }
}