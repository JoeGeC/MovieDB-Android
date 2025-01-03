package com.joe.popularmovies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.compose.SubcomposeAsyncImage
import com.joe.popularmovies.presentation.PopularMoviesViewModel
import com.joe.popularmovies.presentation.model.MovieListItemModel
import com.joe.presentation.R
import com.joe.presentation.ui.AnimatedCircularProgressBar
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMoviesScreen(
    viewModel: PopularMoviesViewModel = hiltViewModel()
) {
    val movies = viewModel.moviesPager.collectAsLazyPagingItems()

    LazyVerticalStaggeredGrid(
        state = rememberLazyStaggeredGridState(),
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalItemSpacing = 16.dp,
    ) {
        items(
            count = movies.itemCount,
        ) { index ->
            movies[index]?.let { movie -> MovieListItem(movie) }
        }
        when (movies.loadState.append) {
            is LoadState.Loading -> items(2) { MovieListLoadingItem() }
            is LoadState.Error -> items(2) { MovieListErrorItem() }
            else -> {}
        }
    }
}

@Composable
fun MovieListLoadingItem() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(12.dp))
            .shimmer()
    ) {
        Box(Modifier.fillMaxSize())
    }
}

@Composable
fun MovieListErrorItem() {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Text(stringResource(R.string.somethingWentWrong))
    }
}

@Composable
fun MovieListItem(movie: MovieListItemModel) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
    ) {
        Column {
            Box(contentAlignment = Alignment.BottomStart) {
                PosterImage(movie.posterPath)
                AnimatedCircularProgressBar(
                    movie.score,
                    size = 50f,
                    numberTextStyle = MaterialTheme.typography.titleSmall,
                    percentageTextStyle = MaterialTheme.typography.labelSmall,
                    strokeWidth = 4f,
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .offset(y = 20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(top = 10.dp)
            ) {
                Text(movie.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(movie.releaseDate, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}


@Composable
private fun PosterImage(posterImageUrl: String?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8))
    ) {
        SubcomposeAsyncImage(
            model = posterImageUrl,
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            loading = { ShimmerBox(height = 200) },
            error = { painterResource(R.drawable.poster_fallback) }
        )
    }
}

@Composable
fun ShimmerBox(height: Int) {
    Box(
        Modifier
            .shimmer()
            .fillMaxWidth()
            .height(height.dp)
            .background(MaterialTheme.colorScheme.onSurface)
    )
}