package com.csci448.hadam.moviestreamapp.presentation.navigation.specs

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.csci448.hadam.moviestreamapp.R
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel
import com.csci448.hadam.moviestreamapp.util.api.SearchByTitleQueryFetchr
import kotlinx.coroutines.CoroutineScope

object NewMovieScreenSpec : IScreenSpec {
    private const val LOG_TAG = "448.NewVideoScreenSpec"

    override val route = "newVideo"
    override val arguments: List<NamedNavArgument> = emptyList()
    override val title = R.string.new_video_name
    override fun buildRoute(vararg args: String?): String = route

    @Composable
    override fun Content(
        movieViewModel: IMovieViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {

        val searchByTitleQueryFetchr = remember { SearchByTitleQueryFetchr() }
        val apiSearchByTitle = searchByTitleQueryFetchr.searchByTitle
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val movieState = remember {
            mutableStateOf(apiSearchByTitle.value)
        }

        LaunchedEffect(key1 = apiSearchByTitle.value) {
            val autoComplete = apiSearchByTitle.value
            if (autoComplete != null) {
                movieState.value = autoComplete
            }
        }

        val searchText = movieViewModel.currentVideoSearchState
            .collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)
        val video =
            movieViewModel.currentSearchVideoToDisplayState.collectAsStateWithLifecycle(context = coroutineScope.coroutineContext)

//        NewVideoScreen(
//            autoComplete = videoState.value,
//            searchText = searchText.value,
//            imdbViewModel = imdbViewModel,
//            onSaveVideo = {
//                val videoVal = video.value
//                if (videoVal != null) {
//                    val videoToAdd = Video(
//                        id = videoVal.id,
//                        name = videoVal.movie,
//                        rank = videoVal.rank,
//                        year = videoVal.year,
//                        genre = videoVal.type,
//                        actors = videoVal.starts,
//                        imageUrl = if (videoVal.link != null) videoVal.link.imageUrl else "",
//                        favorite = false
//                    )
//                    val exists = imdbViewModel.videoListState.value.contains(videoToAdd)
//                    if (!exists) {
//                        imdbViewModel.addVideo(videoToAdd = videoToAdd)
//                    }
//                    else {
//                        Toast.makeText(context, "${videoToAdd.name} already exists.",
//                            Toast.LENGTH_LONG).show()
//                    }
//                }
//
//                navController.popBackStack(
//                    route = ListScreenSpec.buildRoute(),
//                    inclusive = false
//                )
//            },
//            apiButtonIsEnabled = NetworkConnectionUtil.isNetworkAvailableAndConnected(context),
//            onRequestApiVideo = {
//                imdbQueryFetchr.getVideo(searchText.value)
//            },
//            resetQuery = {
//                videoState.value = null
//                imdbQueryFetchr.resetAutoComplete()
//            }
//        )
    }

    @Composable
    override fun TopAppBarActions(
        movieViewModel: IMovieViewModel,
        navController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {

    }
}