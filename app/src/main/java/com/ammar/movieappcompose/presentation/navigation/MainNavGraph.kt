package com.ammar.movieappcompose.presentation.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ammar.movieappcompose.presentation.screens.MovieDetailsScreen
import com.ammar.movieappcompose.presentation.screens.MovieListScreen
import com.ammar.movieappcompose.presentation.utils.Screen


@Preview
@Composable
fun MainNavGraph (
){
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.List.route) {
        composable(route = Screen.List.route) {
            MovieListScreen(navController)
        }
        composable(route = Screen.Detail.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(500)
                )
            }) {backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")
            requireNotNull(movieId) { "Movie parameter wasn't found. Please make sure its already declared"}
            MovieDetailsScreen(navController, movieId)
        }
    }
}