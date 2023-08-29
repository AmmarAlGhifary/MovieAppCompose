package com.ammar.movieappcompose.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ammar.movieappcompose.R
import com.ammar.movieappcompose.presentation.components.ErrorView
import com.ammar.movieappcompose.presentation.components.LoadingShimmerEffect
import com.ammar.movieappcompose.presentation.utils.Screen
import com.ammar.movieappcompose.presentation.viewmodel.MovieListViewModel
import com.ammar.movieappcompose.util.uiview.MovieView

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieListScreen(navController: NavController, viewModel: MovieListViewModel = hiltViewModel()) {
    val lazyMovieItems = remember {
        viewModel.movieList
    }.collectAsLazyPagingItems()

    val listState = rememberLazyListState()
    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Movie App Compose") }, backgroundColor = Color.White
        )
    }) {
        if (lazyMovieItems.loadState.refresh is LoadState.Error) {
            ErrorView { lazyMovieItems.retry() }
        }
        LazyColumn(state = listState) {
            if (lazyMovieItems.loadState.refresh is LoadState.Loading) {
                items(10) {
                    LoadingShimmerEffect()
                }
            }
            items(lazyMovieItems.itemCount) { index ->
                lazyMovieItems[index]?.let { movie ->
                    MovieListItem(navController = navController, movie)
                }
            }
        }
    }
}

@Composable
fun MovieListItem(navController: NavController, movie: MovieView) {
    Box(
        Modifier
            .clickable { navController.navigate(Screen.Detail.createRoute(movie.id.toString())) }
            .fillMaxWidth()) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(movie.image).crossfade(true)
                    .placeholder(R.drawable.placeholder).error(R.drawable.ic_error_image).build(),
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .height(110.dp)
                    .width(110.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxHeight()
                    .align(CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = movie.title,
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(10.dp))
                Text(text = movie.release_date, color = Color.Black, fontSize = 15.sp)
                Spacer(Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = movie.vote, color = Color.Black, fontSize = 14.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(R.drawable.star),
                        contentDescription = "rating",
                    )
                }
            }
        }
    }
}
