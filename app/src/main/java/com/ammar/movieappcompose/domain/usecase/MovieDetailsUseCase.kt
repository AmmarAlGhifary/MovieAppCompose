package com.ammar.movieappcompose.domain.usecase

import com.ammar.movieappcompose.domain.model.moviedetails.MovieDetailResponse
import com.ammar.movieappcompose.domain.repository.MoviesRepository
import com.ammar.movieappcompose.util.Resource
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    suspend fun invoke(id : Int) : Resource<MovieDetailResponse> {
        return moviesRepository.getMovieDetails(id)
    }
}