package com.github.dillonfarber.thelistoffavorites.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.dillonfarber.thelistoffavorites.CardBlowupActivity
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.IGDBGamesResponse
import com.github.dillonfarber.thelistoffavorites.ui.theme.AshWhite


class SimilarGamesGrid : ComponentActivity() {
    private var gamesList: ArrayList<IGDBGamesResponse> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            gamesList = intent.getParcelableExtra("gamesList", ArrayList<IGDBGamesResponse>()::class.java)!!
        }


        setContent {
            MyView()
        }
    }
    @Composable
    fun MyView(){
        GameGrid(gamesList, this@SimilarGamesGrid)
    }
}

@Composable
fun GameGrid(
    gamesList: ArrayList<IGDBGamesResponse>,
    similarGamesGrid: SimilarGamesGrid
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Similar Games",
                color = AshWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(10.dp),
                content ={
                    items(gamesList.size){index ->
                        AsyncImage(
                            model = "https://images.igdb.com/igdb/image/upload/t_cover_big/${gamesList[index].cover?.imageId}.jpg",
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .clip(RoundedCornerShape(5.dp))
                                .padding(5.dp)
                                .size(120.dp,150.dp)
                                .clickable {
                                    val intent = Intent(similarGamesGrid, CardBlowupActivity::class.java)
                                    intent.putExtra("gameData", gamesList[index])
                                    intent.putExtra("isParent", false)
                                    similarGamesGrid.startActivity(intent)
                                }

                        )
                    }
                }
            )
        }
    }
}