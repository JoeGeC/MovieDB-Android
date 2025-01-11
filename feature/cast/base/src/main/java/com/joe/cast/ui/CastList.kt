package com.joe.cast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.joe.cast.R
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.Person
import com.joe.cast.presentation.state.CastSuccessState
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
private fun PersonListWithTitle(personList: List<Person>, title: String) {
    if (personList.isEmpty()) return
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, start = 26.dp, bottom = 8.dp)
    )
    PersonList(personList)
}

@Composable
private fun PersonList(personList: List<Person>) {
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
private fun CastMemberItem(person: Person) {
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
            PersonImage(person.profilePath, person.name)
            PersonName(person.name)
            PersonSubtitle(person.getListSubtitle())
        }

    }
}

@Composable
private fun PersonImage(profilePath: String?, personName: String) {
    Box(
        Modifier
            .clip(RoundedCornerShape(8))
            .width(Dimensions.PERSON_WIDTH.dp)
            .height(300.dp)
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = profilePath,
            contentDescription = "$personName ${stringResource(R.string.profile)}",
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
private fun PersonName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .padding(top = 8.dp)
    )
}

@Composable
private fun PersonSubtitle(subtitle: String?) {
    if(subtitle.isNullOrEmpty()) return
    Text(
        text = subtitle,
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
