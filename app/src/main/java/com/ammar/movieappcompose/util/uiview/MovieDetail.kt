package com.ammar.movieappcompose.util.uiview

import com.ammar.movieappcompose.domain.model.moviedetails.Genre

data class MovieDetail(
    val title: String,
    val image: String,
    val vote: String,
    val release_date: String?,
    val overView: String,
    val genres: List<Genre>
)
