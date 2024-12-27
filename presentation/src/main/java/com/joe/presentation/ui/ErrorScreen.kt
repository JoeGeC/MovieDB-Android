package com.joe.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Something went wrong :(",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}