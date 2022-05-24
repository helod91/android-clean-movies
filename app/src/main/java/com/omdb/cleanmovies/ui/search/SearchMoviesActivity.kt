package com.omdb.cleanmovies.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.omdb.cleanmovies.common.BaseActivity
import com.omdb.cleanmovies.databinding.ActivitySearchMoviesBinding
import com.omdb.cleanmovies.ui.details.MovieDetailsActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class SearchMoviesActivity :
    BaseActivity<ActivitySearchMoviesBinding, SearchMoviesState, SearchMoviesIntent, SearchMoviesAction, SearchMoviesViewModel>() {

    override val viewModel: SearchMoviesViewModel by viewModel()

    private val adapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSearch()
    }

    override fun presentBinding(): ActivitySearchMoviesBinding =
        ActivitySearchMoviesBinding.inflate(layoutInflater)

    override fun render(state: SearchMoviesState) {
        binding.searchMoviesLoading.isVisible = false
        binding.searchMoviesEmpty.isVisible = false
        adapter.data = emptyList()

        when (state) {
            is SearchMoviesState.Exception -> {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage(state.error?.message)
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            is SearchMoviesState.Loading -> binding.searchMoviesLoading.isVisible = true
            is SearchMoviesState.ResultEmpty -> binding.searchMoviesEmpty.isVisible = true
            is SearchMoviesState.ResultMovies -> adapter.data = state.data
        }
    }

    private fun setupSearch() {
        binding.searchMoviesResults.layoutManager = LinearLayoutManager(this)
        binding.searchMoviesResults.adapter = adapter

        binding.searchMoviesTitleInput.setOnEditorActionListener { view, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)

                dispatchIntent(SearchMoviesIntent.SearchMovies(binding.searchMoviesTitleInput.text.toString()))
            }

            actionId == EditorInfo.IME_ACTION_SEARCH
        }

        adapter.setMovieClickedListener { id, title ->
            val openMovieDetails = Intent(this, MovieDetailsActivity::class.java)
            openMovieDetails.putExtra(MovieDetailsActivity.FLAG_MOVIE_ID, id)
            openMovieDetails.putExtra(MovieDetailsActivity.FLAG_MOVIE_TITLE, title)

            startActivity(openMovieDetails)
        }
    }
}