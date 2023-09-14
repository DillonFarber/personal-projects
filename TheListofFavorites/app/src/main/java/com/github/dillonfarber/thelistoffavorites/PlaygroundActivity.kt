package com.github.dillonfarber.thelistoffavorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView



class PlaygroundActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Preview
@Composable
fun CardBlowup1(){

    Box(modifier = Modifier
        .background(Color.DarkGray)
        .fillMaxSize()
        ){
        Column {
            Image(
                painterResource(id = R.drawable.ghost_of_tsushima_logo_4k_p1_240x320),
                contentDescription = "the background",
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(1 / 3f),
                contentScale = ContentScale.FillBounds
            )
            CoverAndInfo1()
            imageRow1(images = listOf("new", "", "", "", ""))
        }
    }
}


@Composable
fun imageRow1(
    images: List<String>
){
    val context = LocalContext.current
    var selectedImageIndex by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier) {
        LazyRow {
            items(images.size) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                        .clickable {
                            selectedImageIndex = it
                        }
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            if (selectedImageIndex == it) Color.Black
                            else Color.DarkGray
                        )
                        .padding(15.dp)
                ) {
                 AndroidView(factory = {
                     contect ->
                     PlayerView(context).also{
                         it.player = ExoPlayer.Builder(context).build().apply{
                             setMediaItem(MediaItem.fromUri(images[selectedImageIndex]))
                             playWhenReady = true
                             prepare()
                         }
                     }
                 })
                }
            }
        }
    }
}

@Composable
fun CoverAndInfo1()
{
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, (-30).dp)
    )
    {
        Row(

        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.controller),
                    contentDescription = "Cover Image of the current game selection",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .size(150.dp),
                    contentScale = ContentScale.Fit
                )
            }
            Column(
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Ghost of Tsushima",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = "Genres: ",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Roleplaying Game (RPG),\nOpen World,\nAdventure,\nAction",
                    color = colorResource(R.color.ashWhite),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 25.dp)
                )
                Text(
                    text = "Platforms: ",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "PS5",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 25.dp)
                )
            }
        }
    }
}