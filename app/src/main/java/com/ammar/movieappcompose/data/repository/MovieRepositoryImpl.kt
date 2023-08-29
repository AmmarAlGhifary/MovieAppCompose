package com.ammar.movieappcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ammar.movieappcompose.data.di.ApiService
import com.ammar.movieappcompose.data.source.MoviesDataSource
import com.ammar.movieappcompose.domain.model.moviedetails.MovieDetailResponse
import com.ammar.movieappcompose.domain.repository.MoviesRepository
import com.ammar.movieappcompose.util.uiview.MovieView
import com.ammar.movieappcompose.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val api: ApiService) : MoviesRepository,
        BaseRepository() {

    override fun getMovies(): Flow<PagingData<MovieView>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 25),
            pagingSourceFactory = {
                MoviesDataSource(api)
            }
        ).flow
    }

    override suspend fun getMovieDetails(id: Int): Resource<MovieDetailResponse> {
        return safeApiCall { api.getMoviesDetail(id) }
    }

}