package com.csci448.hadam.moviestreamapp.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.hadam.moviestreamapp.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel

@Composable
fun MovieTopBar(
    movieViewModel: IMovieViewModel,
    navController: NavHostController,
    context: Context
) {
    val navBackStackEntryState = navController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        movieViewModel = movieViewModel,
        navController = navController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context
    )
}