package com.ammar.movieappcompose.presentation.utils

sealed class Screen(val route: String) {
    data object List : Screen("list")
    data object Search : Screen("search")
    data object Profile : Screen("profile")

    data object Detail : Screen("{movieId}/detail")

    fun createRoute(movieId: String) = "$movieId/detail"
}
