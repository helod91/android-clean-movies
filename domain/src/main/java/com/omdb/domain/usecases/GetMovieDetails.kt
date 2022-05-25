package com.omdb.domain.usecases

import com.omdb.domain.models.Data
import com.omdb.domain.models.MovieDetailsModel
import com.omdb.domain.models.error.DomainErrorCode
import com.omdb.domain.models.error.DomainException
import com.omdb.domain.repositories.MovieRepository
import com.omdb.domain.repositories.NetworkInfoProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class GetMovieDetails(
    private val moviesRepository: MovieRepository,
    private val networkInfoProvider: NetworkInfoProvider
) :
    UseCase<GetMovieDetails.Params, MovieDetailsModel>() {

    override suspend fun provideData(params: Params): Data<MovieDetailsModel> {
        if (networkInfoProvider.hasInternetConnection().not()) {
            throw DomainException(DomainErrorCode.NO_INTERNET_CONNECTION)
        }

        return Data.success(moviesRepository.getMovieDetails(params.id))
    }

    data class Params(val id: String?)
}