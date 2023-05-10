package com.csci448.hadam.moviestreamapp.presentation.navigation.specs

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.moviestreamapp.R
import com.csci448.hadam.moviestreamapp.presentation.list.MovieListScreen
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel
import kotlinx.coroutines.CoroutineScope

object ListScreenSpec : IScreenSpec {
    override val route = "list"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.list_name
    override fun buildRoute(vararg args: String?) = route

    @Composable
    override fun Content(
        movieViewModel: IMovieViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val videos = movieViewModel.movieListState.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
//        movieViewModel.resetSearch()
        MovieListScreen(
            movieDataList = videos.value,
            onSelectVideo = { id ->
                navController.navigate("detail/${id}")
            },
            movieViewModel = movieViewModel
        )
    }

    @Composable
    override fun TopAppBarActions(
        movieViewModel: IMovieViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        IconButton(onClick = { navController.navigate(route = NewMovieScreenSpec.route) }) {
            Icon(
                imageVector = Icons.Filled.AddCircle,
                contentDescription = stringResource(R.string.list_name)
            )
        }
    }
}