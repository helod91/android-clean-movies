package com.omdb.domain.usecases

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieSearchResultModel
import com.omdb.domain.models.error.DomainErrorCode
import com.omdb.domain.models.error.DomainException
import com.omdb.domain.repositories.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SearchMovies(private val moviesRepository: MovieRepository) :
    UseCase<SearchMovies.Params, MovieSearchResultModel>() {

    override suspend fun provideData(params: Params): Data<MovieSearchResultModel> {
        val randomError = (0..100).random()
        if (randomError % 2 ==0) {
            throw DomainException(DomainErrorCode.TEST_ERROR)
        }

        return Data.success(moviesRepository.searchMovies(params.title))
    }

    data class Params(val title: String?)
}