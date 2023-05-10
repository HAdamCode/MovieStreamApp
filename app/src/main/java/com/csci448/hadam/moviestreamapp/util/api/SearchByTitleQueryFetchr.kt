package com.csci448.hadam.moviestreamapp.util.api

import android.util.Log
import com.csci448.hadam.moviestreamapp.data.SearchByTitle.SearchByTitle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchByTitleQueryFetchr {
    companion object {
        private const val LOG_TAG = "448.Fetchr"
    }

    fun getVideo(searchText: String?) {
        if (searchText == null) {
            mAutoComplete.update { null }
            return
        }
        Log.e(LOG_TAG, searchText)
        val searchByTitleRequest = searchByTitleApiService.getSearchMovies(searchText)

        searchByTitleRequest.enqueue(object : Callback<SearchByTitle> {
            override fun onFailure(call: Call<SearchByTitle>, t: Throwable) {
                Log.e(LOG_TAG, "onFailure() called $t")
            }


            override fun onResponse(
                call: Call<SearchByTitle>,
                response: Response<SearchByTitle>
            ) {
                Log.d(LOG_TAG, "onResponse() called")
                val responseCharacter = response.body()
                if (responseCharacter == null) {
                    Log.d(LOG_TAG, "response character is null")
                    mAutoComplete.update { null }
                } else {
                    Log.d(LOG_TAG, responseCharacter.result.size.toString())
                    mAutoComplete.update { responseCharacter }
                }
            }
        })
    }
    private val searchByTitleApiService: SearchByTitleApiService
    private val mAutoComplete = MutableStateFlow<SearchByTitle?>(null)

    val AutoComplete: StateFlow<SearchByTitle?>
        get() = mAutoComplete.asStateFlow()


    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(SearchByTitleApiService.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        searchByTitleApiService = retrofit.create(SearchByTitleApiService::class.java)
    }
}