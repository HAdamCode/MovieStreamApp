package com.csci448.hadam.moviestreamapp.presentation.viewmodel

import com.csci448.hadam.moviestreamapp.data.MovieData
import kotlinx.coroutines.flow.StateFlow

interface IMovieViewModel {
    val movieListState: StateFlow<List<MovieData>>
    val currentMovieState: StateFlow<MovieData?>
    fun loadMovieByUUID(uuid: String)
    fun addMovie(movieToAdd: MovieData)
    fun deleteMovie(movieToDelete: MovieData)
}