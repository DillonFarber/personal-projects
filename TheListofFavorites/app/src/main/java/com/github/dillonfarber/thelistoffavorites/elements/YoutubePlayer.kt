package com.github.dillonfarber.thelistoffavorites.elements

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(
    youtubeVideoId: List<String>,
    lifecycleOwner: LifecycleOwner
) {
    var selectedItem by remember { mutableStateOf(0) }
    var youtubePlayer: YouTubePlayer? = null
    LazyRow(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
    ){
        itemsIndexed(youtubeVideoId) { index, item ->
            AndroidView(
                modifier = Modifier
                    .size(300.dp, 250.dp)
                    .padding(start = 5.dp, top = 5.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(15.dp)),
                factory = { context ->

                    YouTubePlayerView(context = context).apply {
                        lifecycleOwner.lifecycle.addObserver(this)
                        addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youtubePlayer = youTubePlayer
                                youTubePlayer.cueVideo(item, 0f)
                            }
                        })
                    }
                }
            )
        }
    }
}