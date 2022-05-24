package com.omdb.cleanmovies.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.omdb.cleanmovies.common.BaseAdapter
import com.omdb.cleanmovies.databinding.ItemMovieBinding
import com.omdb.domain.models.MovieSearchModel

class MoviesAdapter : BaseAdapter<ItemMovieBinding, MovieSearchModel>() {

    private var movieClickedListener: ((id: String, title: String?) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder<ItemMovieBinding>, position: Int) {
        with(holder.binding) {
            data?.get(position)?.let { movie ->
                movieTitle.text = movie.title
                movieReleaseYear.text = movie.releaseYear
                movieThumb.load(movie.thumbUrl)

                root.setOnClickListener {
                    movieClickedListener?.invoke(movie.id, movie.title)
                }
            }
        }
    }

    override fun presentBinding(parent: ViewGroup): ItemMovieBinding = ItemMovieBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
    )

    fun setMovieClickedListener(listener: ((id: String, title: String?) -> Unit)? = null) {
        movieClickedListener = listener
    }
}