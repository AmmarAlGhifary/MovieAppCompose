package com.ammar.movieappcompose.domain.usecase

import androidx.paging.PagingData
import com.ammar.movieappcompose.domain.repository.MoviesRepository
import com.ammar.movieappcompose.util.uiview.MovieView
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    fun invoke() : Flow<PagingData<MovieView>> {
        return moviesRepository.getMovies()
    }
}