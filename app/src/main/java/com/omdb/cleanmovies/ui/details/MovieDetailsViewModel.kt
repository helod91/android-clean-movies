package com.omdb.cleanmovies.ui.details

import com.omdb.cleanmovies.common.BaseViewModel
import com.omdb.domain.usecases.GetMovieDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
class MovieDetailsViewModel(
    private val movieDetailsUseCase: GetMovieDetails
) : BaseViewModel<MovieDetailsState, MovieDetailsIntent, MovieDetailsAction>() {

    override fun intentToAction(intent: MovieDetailsIntent): MovieDetailsAction {
        return when (intent) {
            is MovieDetailsIntent.LoadMovieDetails -> MovieDetailsAction.MovieDetails(intent.movieId)
        }
    }

    override fun handleAction(action: MovieDetailsAction) {
        launchOnUI {
            when (action) {
                is MovieDetailsAction.MovieDetails -> {
                    movieDetailsUseCase.execute(GetMovieDetails.Params(action.movieId))
                        .collectLatest { result ->
                            state.value = result.reduce()
                        }
                }
            }
        }
    }
}