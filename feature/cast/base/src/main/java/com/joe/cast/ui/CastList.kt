package com.joe.cast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.PersonModel
import com.joe.cast.presentation.state.CastSuccessState
import com.joe.presentation.R
import com.joe.presentation.ui.ErrorScreen
import com.joe.presentation.ui.ShimmerBox
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.ViewModelState

@Composable
fun CastListState(state: ViewModelState, refresh: (() -> Unit)? = null) {
    when (state) {
        is CastSuccessState -> CastSuccessScreen(state.castListModel)
        is ErrorState -> ErrorScreen(refresh)
        is LoadingState -> CastListLoadingScreen()
    }
}

@Composable
fun CastSuccessScreen(castList: CastListModel) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(castList.cast.size) { index ->
            CastMemberItem(castList.cast[index])
        }
    }
}

@Composable
fun CastMemberItem(person: PersonModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = person.profilePath,
            contentDescription = stringResource(R.string.movie_poster),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            loading = { ShimmerBox() },
            error = {
                Image(
                    painter = painterResource(R.drawable.poster_fallback),
                    contentDescription = stringResource(R.string.person_fallback)
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = person.name, fontWeight = FontWeight.Bold)
        Text(text = person.character, style = MaterialTheme.typography.bodyMedium)
    }
}
