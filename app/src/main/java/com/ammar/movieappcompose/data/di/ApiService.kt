package com.ammar.movieappcompose.data.di

import com.ammar.movieappcompose.domain.model.moviedetails.MovieDetailResponse
import com.ammar.movieappcompose.domain.model.movies.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("page") page : Int? = null,
    ) : MovieResponse

    @GET("movie/{id}")
    suspend fun getMoviesDetail(
        @Path("id") id : Int
    ) : MovieDetailResponse
}