package com.csci448.hadam.moviestreamapp.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieViewModelFactory(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
        companion object {
            private const val LOG_TAG = "448.SamodelkinViewModelFactory"
        }

        fun getViewModelClass() = MovieViewModel::class.java

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Log.d(LOG_TAG, "create() called")
            if (modelClass.isAssignableFrom(getViewModelClass())) {
                Log.d(LOG_TAG, "creating ViewModel: ${getViewModelClass()}")
                return modelClass
                    .getConstructor()
                    .newInstance()
            }
            Log.e(LOG_TAG, "Unknown ViewModel: $modelClass")
            throw IllegalArgumentException("Unknown ViewModel")
        }
}