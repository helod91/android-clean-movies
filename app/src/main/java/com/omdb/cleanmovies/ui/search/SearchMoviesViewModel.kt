package com.omdb.cleanmovies.ui.search

import com.omdb.cleanmovies.common.BaseViewModel
import com.omdb.domain.usecases.SearchMovies
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest

@ExperimentalCoroutinesApi
class SearchMoviesViewModel(
    private val searchMoviesUseCase: SearchMovies
) : BaseViewModel<SearchMoviesState, SearchMoviesIntent, SearchMoviesAction>() {

    override fun intentToAction(intent: SearchMoviesIntent): SearchMoviesAction {
        return when (intent) {
            is SearchMoviesIntent.SearchMovies -> SearchMoviesAction.SearchMovies(intent.query)
        }
    }

    override fun handleAction(action: SearchMoviesAction) {
        launchOnUI {
            when (action) {
                is SearchMoviesAction.SearchMovies ->
                    searchMoviesUseCase.execute(SearchMovies.Params(action.query))
                        .collectLatest { result ->
                            state.value = result.reduce()
                        }
            }
        }
    }
}