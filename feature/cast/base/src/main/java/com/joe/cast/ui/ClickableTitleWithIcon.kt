package com.joe.cast.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joe.cast.R

@Composable
fun ClickableTitleWithIcon(
    title: String,
    iconAnimatedRotation: Float = 0f,
    onClick: (() -> Unit)? = null,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp)
        .clickable { onClick?.invoke() }
        .padding(top = 8.dp, start = 26.dp, bottom = 8.dp)
    ) {
        Title(title)
        DropDownIcon(iconAnimatedRotation)
    }
}


@Composable
private fun Title(title: String) {
    Text(
        title,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun DropDownIcon(rotationAngle: Float) {
    Icon(
        imageVector = Icons.Rounded.ArrowDropDown,
        contentDescription = stringResource(R.string.show_cast_members),
        tint = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier.rotate(rotationAngle)
    )
}