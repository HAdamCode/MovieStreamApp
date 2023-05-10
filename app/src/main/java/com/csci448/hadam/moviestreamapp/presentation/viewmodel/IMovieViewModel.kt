package com.csci448.hadam.moviestreamapp.presentation.viewmodel

import com.csci448.hadam.moviestreamapp.data.MovieData
import com.csci448.hadam.moviestreamapp.data.SearchByTitle.Result
import com.csci448.hadam.moviestreamapp.data.SearchByTitle.SearchByTitle
import kotlinx.coroutines.flow.StateFlow

interface IMovieViewModel {
    val movieListState: StateFlow<List<MovieData>>
    val currentMovieState: StateFlow<MovieData?>
    val currentVideoSearchState: StateFlow<String>
    val currentSearchVideoToDisplayState: StateFlow<Result?>
    fun loadMovieByUUID(uuid: String)
    fun addMovie(movieToAdd: MovieData)
    fun deleteMovie(movieToDelete: MovieData)
}