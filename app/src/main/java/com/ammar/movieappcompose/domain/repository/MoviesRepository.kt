package com.ammar.movieappcompose.domain.repository

import androidx.paging.PagingData
import com.ammar.movieappcompose.domain.model.moviedetails.MovieDetailResponse
import com.ammar.movieappcompose.util.uiview.MovieView
import com.ammar.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getMovies(): Flow<PagingData<MovieView>>

    suspend fun getMovieDetails(id : Int) : Resource<MovieDetailResponse>
}