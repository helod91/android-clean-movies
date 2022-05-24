package com.omdb.cleanmovies.ui.details

import android.os.Bundle
import coil.load
import com.omdb.cleanmovies.common.BaseActivity
import com.omdb.cleanmovies.databinding.ActivityMovieDetailsBinding
import com.omdb.domain.models.MovieDetailsModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MovieDetailsActivity : BaseActivity<ActivityMovieDetailsBinding, MovieDetailsViewModel>() {

    companion object {
        const val FLAG_MOVIE_ID = "MovieDetailsActivity.MovieId"
        const val FLAG_MOVIE_TITLE = "MovieDetailsActivity.MovieTitle"
    }

    override val viewModel: MovieDetailsViewModel by viewModel()
    override var showBackButton: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeSuccess(viewModel.movieDetailsResult, {
            populateUI(it)
        }, binding.movieDetailsLoading)
        viewModel.getMovieDetails(intent.getStringExtra(FLAG_MOVIE_ID))

        supportActionBar?.title = intent.getStringExtra(FLAG_MOVIE_TITLE)
    }

    override fun presentBinding(): ActivityMovieDetailsBinding =
        ActivityMovieDetailsBinding.inflate(layoutInflater)

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