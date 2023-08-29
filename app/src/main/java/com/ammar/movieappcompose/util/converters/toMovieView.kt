package com.ammar.movieappcompose.util.converters

import com.ammar.movieappcompose.BuildConfig.POSTER_PATH
import com.ammar.movieappcompose.domain.model.movies.Movie
import com.ammar.movieappcompose.util.uiview.MovieView

fun Movie.toMovieView() = MovieView(
    title = title,
    id = id,
    release_date = release_date?.substringBefore("-") ?: "",
    vote = vote_average.toString(),
    image = POSTER_PATH + poster_path
)