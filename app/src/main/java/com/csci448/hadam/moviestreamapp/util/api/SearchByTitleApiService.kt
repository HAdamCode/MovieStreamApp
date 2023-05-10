package com.csci448.hadam.moviestreamapp.util.api

import com.csci448.hadam.moviestreamapp.data.SearchByTitle.SearchByTitle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface SearchByTitleApiService {
    companion object {
        const val BASE_API_URL = "https://streaming-availability.p.rapidapi.com/v2/search"
    }

    @Headers(
        "X-RapidAPI-Key: 4aed1fa553msh9690f994a296a84p189f40jsn356f52792a59",
        "X-RapidAPI-Host: streaming-availability.p.rapidapi.com"
    )
    @GET("title")
    fun getSearchMovies(
        @Query("title") searchText: String,
        @Query("country") country: String = "us",
        @Query("show_type") showType: String = "movie",
        @Query("output_language") outputLanguage: String = "en"
    ): Call<SearchByTitle>
}