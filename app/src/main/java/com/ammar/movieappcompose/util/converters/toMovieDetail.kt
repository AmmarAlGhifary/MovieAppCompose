package com.ammar.movieappcompose.util.converters

import com.ammar.movieappcompose.BuildConfig.POSTER_PATH
import com.ammar.movieappcompose.domain.model.moviedetails.MovieDetailResponse
import com.ammar.movieappcompose.util.uiview.MovieDetail

fun MovieDetailResponse.toMovieDetail() = MovieDetail(
    title = title,
    release_date = release_date.substringBefore("-"),
    vote = vote_average.toString(),
    image = POSTER_PATH + poster_path,
    overView = overview,
    genres = genres
)