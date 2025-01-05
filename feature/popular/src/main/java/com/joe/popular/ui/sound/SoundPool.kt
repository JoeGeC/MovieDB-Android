package com.joe.popular.ui.sound

import android.content.Context
import android.media.SoundPool
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joe.popular.R

@Composable
fun scrollToTopSounds(context: Context, soundPool: SoundPool): Pair<Int, Int> {
    val scrollSound = remember { soundPool.load(context, R.raw.scroll, 1) }
    val popSound = remember { soundPool.load(context, R.raw.pop, 1) }
    return Pair(scrollSound, popSound)
}