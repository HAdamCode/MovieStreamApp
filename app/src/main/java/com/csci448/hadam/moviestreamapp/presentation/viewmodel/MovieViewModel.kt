package com.csci448.hadam.moviestreamapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.csci448.hadam.moviestreamapp.data.MovieData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieViewModel() : IMovieViewModel, ViewModel() {
    private val mMovies: MutableStateFlow<List<MovieData>> =
        MutableStateFlow(emptyList())

    override val movieListState: StateFlow<List<MovieData>>
        get() = mMovies.asStateFlow()

    private val mCurrentMovieState: MutableStateFlow<MovieData?> =
        MutableStateFlow(null)

    override val currentMovieState: StateFlow<MovieData?>
        get() = mCurrentMovieState.asStateFlow()

    override fun loadMovieByUUID(uuid: String) {

    }
    override fun addMovie(movieToAdd: MovieData) {

    }
    override fun deleteMovie(movieToDelete: MovieData) {

    }
}