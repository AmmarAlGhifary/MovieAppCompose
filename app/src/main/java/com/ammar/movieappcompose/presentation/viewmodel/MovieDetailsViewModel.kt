package com.ammar.movieappcompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ammar.movieappcompose.domain.usecase.MovieDetailsUseCase
import com.ammar.movieappcompose.util.Resource
import com.ammar.movieappcompose.util.UiState
import com.ammar.movieappcompose.util.converters.toMovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailsUseCase) :
    ViewModel() {

        private val _detailResponse : MutableStateFlow<UiState> =
            MutableStateFlow(UiState(true, null, false))
    val movieDetail get() = _detailResponse

    fun getMovieDetail(id: Int) = viewModelScope.launch {
        _detailResponse.emit(UiState(true, null, false))
        when (val response = movieDetailsUseCase.invoke(id)) {
            is Resource.Success -> {
                _detailResponse.emit(UiState(false, response.value.toMovieDetail(), false))
            }
            is Resource.Failure -> {
                _detailResponse.emit(UiState(false, null, true))
            }

        }
    }
}