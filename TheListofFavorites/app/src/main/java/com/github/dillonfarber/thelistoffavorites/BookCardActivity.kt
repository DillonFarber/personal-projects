package com.github.dillonfarber.thelistoffavorites

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks.BookInfo
import com.github.dillonfarber.thelistoffavorites.ui.theme.AshWhite

class BookCardActivity : ComponentActivity() {
    var book: BookInfo = BookInfo()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        book = intent.getParcelableExtra("bookDetails", BookInfo::class.java)!!
        setContent {
            BookCard()
        }
    }
    @Preview
    @Composable
    fun BookCard(){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ){
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp)
            ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(75.dp)
                    )
                    BookCover_Title()
                    Details()
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )
                    Summary()
            }
        }
    }
}

@Composable
fun BookCover_Title(){
    Box(
        modifier = Modifier
            .background(Color.Black)
    ){
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(start = 5.dp, top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.tokyo_ghoul),
                    modifier = Modifier
                        .height(250.dp)
                        .width(225.dp),
                    contentDescription = "Book cover"
                )
            }
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Tokyo Ghoul",
                    color = AshWhite,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                    )
                Text(
                    text = "by \nR. A. Salvitor",
                    color = AshWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 10.dp, bottom = 25.dp)
                )
            }
        }
    }
}

@Composable
fun Details(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Genre:",
                    color = AshWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
                Text(
                    text = "genres",
                    color = AshWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .width(20.dp)
            )
            Column {
                Text(
                    text = "Pages: ",
                    color = AshWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "1000",
                    color = AshWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                PublishingInfo()
            }
        }
    }
}

@Composable
fun Summary(){
    Box(

    ){
        Column() {
            Text(
                text = "My books Summery",
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PublishingInfo(){
    Box(
        modifier = Modifier
    ){
        Column() {
            Text(
                text = "Publish Date:",
                color = AshWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Date",
                fontSize = 18.sp,
                color = AshWhite,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
            Text(
                text = "Publisher:",
                color = AshWhite,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "CompanyName",
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
    }
}