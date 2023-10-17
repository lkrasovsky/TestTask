package com.example.testtask.presentation.screens.player


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayer(videoUrl: String, onClose: () -> Unit) {

    val context = LocalContext.current
    val player = ExoPlayer.Builder(context).build()
    val mediaItem = MediaItem.fromUri(videoUrl)
    player.setMediaItem(mediaItem)

    DisposableEffect(Unit) {
        player.prepare()
        player.playWhenReady = true
        onDispose {
            player.release()
        }
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {}, navigationIcon = {
            IconButton(onClick = {
                onClose()
                player.release()
            }
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = "Close")
            }
        })
    }) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {

            AndroidView(
                factory = {
                    PlayerView(context).also {
                        it.player = player
                    }
                },
            )
            player.play()
        }
    }
}
