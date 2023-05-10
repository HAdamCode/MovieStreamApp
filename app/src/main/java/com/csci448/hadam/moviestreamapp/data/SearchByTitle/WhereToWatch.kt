package com.csci448.hadam.moviestreamapp.data.SearchByTitle

data class WhereToWatch(
    val type: String,
    val quality: String,
    val addOn: String,
    val link: String,
    val watchLink: String,
    // val audios: List<Audios>,
    // val subtitles: List<Subtitles>,
    val price: Price,
    val leaving: Int
)
