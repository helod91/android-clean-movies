package com.omdb.cleanmovies.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.omdb.domain.usecases.SearchMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMovies
) : ViewModel() {

    private val movieTitle = MutableLiveData<String?>()

    val searchMoviesResult = movieTitle.switchMap { title ->
        liveData {
            searchMoviesUseCase.execute(SearchMovies.Params(title))
                .collectLatest { result ->
                    emit(result)
                }
        }
    }

    fun searchMovie(title: String?) {
        movieTitle.value = title
    }
}