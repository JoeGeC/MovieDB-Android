package com.joe.popular.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joe.popular.presentation.model.MediaListItemModel
import com.joe.presentation.ui.AnimatedScoreCircle
import com.joe.presentation.ui.PosterImage
import com.valentinilk.shimmer.Shimmer

@Composable
fun MediaListItem(
    movie: MediaListItemModel,
    onClick: (() -> Unit)? = null,
    shimmerInstance: Shimmer
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable(onClick = { onClick?.invoke() })
    ) {
        Column {
            Box(contentAlignment = Alignment.BottomStart) {
                PosterImage(
                    movie.posterPath,
                    shimmerInstance,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.667f)
                )
                AnimatedScoreCircle(
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