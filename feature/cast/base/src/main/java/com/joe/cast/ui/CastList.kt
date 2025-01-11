package com.joe.cast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.PersonModel
import com.joe.cast.presentation.state.CastSuccessState
import com.joe.cast.R
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
private fun CastSuccessScreen(castList: CastListModel) {
    Column {
        PersonListWithTitle(castList.cast, stringResource(R.string.cast))
        PersonListWithTitle(castList.crew, stringResource(R.string.crew))
    }
}

@Composable
private fun PersonListWithTitle(personList: List<PersonModel>, title: String) {
    if (personList.isEmpty()) return
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, start = 26.dp, bottom = 8.dp)
    )
    PersonList(personList)
}

@Composable
private fun PersonList(personList: List<PersonModel>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 26.dp),
    ) {
        items(personList.size) { index ->
            CastMemberItem(personList[index])
        }
    }
}

@Composable
private fun CastMemberItem(person: PersonModel) {
    Surface(
        shape = RoundedCornerShape(14.dp),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 8.dp,
        modifier = Modifier
            .width(Dimensions.PERSON_WIDTH.dp)
            .padding(horizontal = 8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            PersonImage(person)
            PersonName(person)
            CharacterName(person)
        }

    }
}

@Composable
private fun PersonImage(person: PersonModel) {
    Box(
        Modifier
            .clip(RoundedCornerShape(8))
            .width(Dimensions.PERSON_WIDTH.dp)
            .height(300.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = person.profilePath,
            contentDescription = "${person.name} ${stringResource(R.string.profile)}",
            contentScale = ContentScale.Crop,
            loading = { ShimmerBox() },
            error = {
                Image(
                    painter = painterResource(R.drawable.profile_fallback),
                    contentDescription = stringResource(R.string.profile_fallback),
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        )
    }
}

@Composable
private fun PersonName(person: PersonModel) {
    Text(
        text = person.name,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .padding(top = 8.dp)
    )
}

@Composable
private fun CharacterName(person: PersonModel) {
    Text(
        text = person.character,
        style = MaterialTheme.typography.labelMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(horizontal = 6.dp)
            .padding(top = 4.dp, bottom = 6.dp)
            .height(height = 40.dp)
    )
}
