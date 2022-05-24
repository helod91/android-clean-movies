package com.omdb.cleanmovies.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.omdb.domain.usecases.GetMovieDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
class MovieDetailsViewModel(
    private val movieDetailsUseCase: GetMovieDetails
) : ViewModel() {

    private val movieId = MutableLiveData<String?>()

    val movieDetailsResult = movieId.switchMap { id ->
        liveData {
            movieDetailsUseCase.execute(GetMovieDetails.Params(id))
                .collectLatest { result ->
                    emit(result)
                }
        }
    }

    fun getMovieDetails(id: String?) {
        movieId.value = id
    }
}