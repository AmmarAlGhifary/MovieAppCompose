package com.ammar.movieappcompose.util

import com.ammar.movieappcompose.util.uiview.MovieDetail

data class UiState(
    val isLoading: Boolean = false,
    val data: MovieDetail? = null,
    val error: Boolean = false
)