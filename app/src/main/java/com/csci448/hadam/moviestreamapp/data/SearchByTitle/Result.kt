package com.csci448.hadam.moviestreamapp.data.SearchByTitle

data class Result(
    val type: String,
    val title: String,
    val overview: String,
    val streamingInfo: StreamingInfo?,
    val cast: List<Cast>,
    val year: Int,
    val advisedMinimumAudienceAge: Int,
    val imdbId: String,
    val imdbRating: Int,
    val imdbVoteCount: Int,
    val tmdbId: Int,
    val tmdbRating: Int,
    val originalTitle: String,
    val backdropPath: String,
//    val backdropURLs: BackdropURLs,
    val genres: List<Genres>,
    val originalLanguage: String,
//    val countries: List<Countries>,
//    val directors: List<Directors>,
    val runtime: Int,
    val youtubeTrailerVideoId: String,
    val youtubeTrailerVideoLink: String,
    val posterPath: String,
//    val posterURLs: PosterURLs,
    val tagline: String
)
