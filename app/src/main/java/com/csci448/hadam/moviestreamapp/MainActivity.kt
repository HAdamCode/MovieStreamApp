package com.csci448.hadam.moviestreamapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.hadam.moviestreamapp.presentation.navigation.MovieNavHost
import com.csci448.hadam.moviestreamapp.presentation.navigation.MovieTopBar
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.IMovieViewModel
import com.csci448.hadam.moviestreamapp.presentation.viewmodel.MovieViewModelFactory
import com.csci448.hadam.moviestreamapp.ui.theme.MovieStreamAppTheme
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

class MainActivity : ComponentActivity() {

    lateinit var storage: FirebaseStorage
    private lateinit var movieViewModel: IMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val signInLauncher = registerForActivityResult(
            FirebaseAuthUIActivityResultContract()
        ) { res ->
            onSignInResult(res)
        }

        storage = Firebase.storage
        createSignInIntent(signInLauncher)
        val factory = MovieViewModelFactory(this)
        movieViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
        setContent {
            MainActivityContent(movieViewModel = movieViewModel)
        }
    }
}

@Composable
private fun MainActivityContent(movieViewModel: IMovieViewModel) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    MovieStreamAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(topBar = {
                MovieTopBar(
                    movieViewModel = movieViewModel,
                    navController = navController,
                    context = context
                )
            }) {
                MovieNavHost(
                    Modifier.padding(it),
                    navController,
                    movieViewModel,
                    context,
                    coroutineScope
                )
            }
        }
    }
}

private fun createSignInIntent(signInLauncher: ActivityResultLauncher<Intent>) {
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build()
    )
    Log.d("SIGNININTENT", "Before signInIntent")
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()
    Log.d("SIGNININTENT", "After signInIntent")
    signInLauncher.launch(signInIntent)
}

private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult): FirebaseUser? {
    if (result.resultCode == AppCompatActivity.RESULT_OK) {
        val user = FirebaseAuth.getInstance().currentUser
        return user
    } else {
        return null
    }
}