package com.ammar.movieappcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ammar.movieappcompose.domain.usecase.MoviesUseCase
import com.ammar.movieappcompose.util.uiview.MovieView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val movieListUseCase: MoviesUseCase) :
    ViewModel() {

    val movieList : Flow<PagingData<MovieView>> get() = getMovies()

    private fun getMovies() = movieListUseCase.invoke().cachedIn(viewModelScope)
}