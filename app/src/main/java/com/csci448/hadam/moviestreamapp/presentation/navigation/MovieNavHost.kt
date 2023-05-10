package com.csci448.hadam.moviestreamapp.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.csci448.hadam.moviestreamapp.presentation.navigation.specs.IScreenSpec
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MovieNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    movieViewModel: IMovieViewModel,
    context: Context,
    coroutineScope: CoroutineScope
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if (screen != null) {
                    composable(
                        route = screen.route,
                        arguments = screen.arguments
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            movieViewModel = movieViewModel,
                            context = context,
                            coroutineScope = coroutineScope
                        )
                    }
                }
            }
        }
    }
}