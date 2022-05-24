package com.omdb.domain.usecases

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.repositories.MovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class GetMovieDetails(private val movieRepository: MovieRepository) :
    UseCase<GetMovieDetails.Params, MovieDetailsModel>() {

    override suspend fun provideData(params: Params): Data<MovieDetailsModel> {
        return Data.success(movieRepository.getMovieDetails(params.id))
    }

    data class Params(val id: String?)
}