package com.csci448.hadam.moviestreamapp.presentation.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.csci448.hadam.moviestreamapp.R
import com.csci448.hadam.moviestreamapp.data.MovieData
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel

@Composable
fun MovieListScreen(
    movieDataList: List<MovieData>,
    onSelectVideo: (String) -> Unit,
    movieViewModel: IMovieViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(movieDataList) { movieData ->
            MovieListItem(
                movieData = movieData,
//                onVideoClick = {
//                    onSelectVideo(movie.id)
//                },
//                onFavoriteClick = {
//                    imdbViewModel.toggleFavorite(movie.id, movie)
//                },
//                isFavorite = movie.favorite
            )
        }
    }
}