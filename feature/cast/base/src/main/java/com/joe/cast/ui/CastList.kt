package com.joe.cast.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.joe.cast.R
import com.joe.cast.presentation.model.CastListModel
import com.joe.cast.presentation.model.Person
import com.joe.cast.presentation.state.CastSuccessState
import com.joe.presentation.ui.ShimmerBox
import com.joe.presentation.viewModels.ErrorState
import com.joe.presentation.viewModels.LoadingState
import com.joe.presentation.viewModels.RefreshingState
import com.joe.presentation.viewModels.ViewModelState

@Composable
fun CastListState(state: ViewModelState, detailsListState: ScrollState, onRefresh: () -> Unit) {
    when (state) {
        is CastSuccessState -> CastSuccessScreen(state.castListModel, detailsListState)
        is ErrorState -> CastListErrorScreen(detailsListState, onRefresh)
        is LoadingState -> CastListLoadingScreen(detailsListState)
        is RefreshingState -> CastListLoadingScreen(detailsListState)
    }
}

@Composable
private fun CastSuccessScreen(castList: CastListModel, detailsListState: ScrollState) {
    Column {
        AnimatedExpandablePersonListWithTitle(
            stringResource(R.string.cast),
            detailsListState,
            castList.cast,
            true
        )
        Spacer(Modifier.height(8.dp))
        AnimatedExpandablePersonListWithTitle(
            stringResource(R.string.crew),
            detailsListState,
            castList.crew,
            false
        )
    }
}

@Composable
private fun AnimatedExpandablePersonListWithTitle(
    title: String,
    detailsListState: ScrollState,
    personList: List<Person>,
    openOnStart: Boolean
) {
    if (personList.isEmpty()) return
    AnimatedExpandableHeaderList(
        baseListState = detailsListState,
        openOnStart = openOnStart,
    ) { isExpanded, onToggleExpand, contentAnimatedHeight, iconAnimatedRotation, contentListState ->
        ExpandableContentWithTitle(
            contentAnimatedHeight,
            isExpanded,
            { ClickableTitleWithIcon(title, iconAnimatedRotation, onToggleExpand) },
            { PersonList(personList, contentListState) }
        )
    }
}

@Composable
fun ExpandableContentWithTitle(
    contentAnimatedHeight: Dp,
    isExpanded: Boolean,
    title: @Composable () -> Unit,
    content: @Composable () -> Unit,
){
    Column(Modifier.fillMaxWidth()) {
        title()
        Box(modifier = Modifier.height(contentAnimatedHeight)) {
            if (isExpanded) {
                content()
            }
        }
    }
}

@Composable
private fun PersonList(personList: List<Person>, state: LazyListState) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 26.dp),
        state = state
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
    if (subtitle.isNullOrEmpty()) return
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
