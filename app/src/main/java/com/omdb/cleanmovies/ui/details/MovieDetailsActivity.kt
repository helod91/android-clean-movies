package com.omdb.cleanmovies.ui.details

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import coil.load
import com.omdb.cleanmovies.common.BaseActivity
import com.omdb.cleanmovies.databinding.ActivityMovieDetailsBinding
import com.omdb.domain.models.MovieDetailsModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, MovieDetailsState,
        MovieDetailsIntent, MovieDetailsAction, MovieDetailsViewModel>() {

    companion object {
        const val FLAG_MOVIE_ID = "MovieDetailsActivity.MovieId"
        const val FLAG_MOVIE_TITLE = "MovieDetailsActivity.MovieTitle"
    }

    override val viewModel: MovieDetailsViewModel by viewModel()
    override var showBackButton: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = intent.getStringExtra(FLAG_MOVIE_TITLE)

        dispatchIntent(MovieDetailsIntent.LoadMovieDetails(intent.getStringExtra(FLAG_MOVIE_ID)))
    }

    override fun presentBinding(): ActivityMovieDetailsBinding =
        ActivityMovieDetailsBinding.inflate(layoutInflater)

    override fun render(state: MovieDetailsState) {
        binding.movieDetailsLoading.isVisible = false
        binding.movieDetailsEmpty.isVisible = false

        when (state) {
            is MovieDetailsState.GenericError -> {
                AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Something went wrong")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            is MovieDetailsState.Loading -> binding.movieDetailsLoading.isVisible = true
            is MovieDetailsState.ResultEmpty -> binding.movieDetailsEmpty.isVisible = true
            is MovieDetailsState.ResultMovieDetails -> populateUI(state.data)
        }
    }

    private fun populateUI(details: MovieDetailsModel?) {
        with(binding) {
            movieDetailsPoster.load(details?.imageUrl)
            movieDetailsTitle.text = details?.title
            movieDetailsReleaseYear.text = details?.releaseDate
            movieDetailsGenre.text = "Genre: ${details?.genre}"
            movieDetailsRuntime.text = "Runtime: ${details?.runtime}"
            movieDetailsWriters.text = "Writers: ${details?.writers}"
            movieDetailsDirectors.text = "Directors: ${details?.directors}"
            movieDetailsActors.text = "Actors: ${details?.actors}"
            movieDetailsPlot.text = details?.plot
        }
    }
}