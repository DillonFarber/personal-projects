package com.github.dillonfarber.thelistoffavorites


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.github.dillonfarber.thelistoffavorites.APIs.IGDB_API
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.Companies_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.Websites_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.IGDBGamesResponse
import com.github.dillonfarber.thelistoffavorites.elements.WebsiteIconGrid
import com.github.dillonfarber.thelistoffavorites.elements.YoutubePlayer
import com.github.dillonfarber.thelistoffavorites.ui.SimilarGamesGrid
import com.github.dillonfarber.thelistoffavorites.ui.ui.theme.AshWhite

class CardBlowupActivity : AppCompatActivity() {
    private var game: IGDBGamesResponse = IGDBGamesResponse()
    private val genreString: ArrayList<String> = arrayListOf()
    private val platString: ArrayList<String> = arrayListOf()
    private var artIDs: MutableList<String> = mutableListOf()
    private var vidsIds: MutableList<String> = mutableListOf()
    private var websites: MutableList<Websites_IGDB> = mutableListOf()
    private val companyList: ArrayList<Companies_IGDB> = arrayListOf()
    private var isParent: Boolean = true
    private var token: BearerTokenResponse = BearerTokenResponse()
    var gameData: ArrayList<IGDBGamesResponse>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isParent = intent.getBooleanExtra("isParent", true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            game = intent.getParcelableExtra("gameData", IGDBGamesResponse::class.java)!!
        }

        game.videos.forEach { vid -> vidsIds.add(vid.videoId!!) }
        game.artworks.forEach { artworkIgdb -> artIDs.add(artworkIgdb.imageId!!) }
        game.screenshots.forEach { screenshot -> artIDs.add(screenshot.imageId!!) }
        websites = game.websites
        game.genres.forEach { genreString.add(it.name!!) }
        game.platforms.forEach { platString.add(it.name!!) }
        game.involvedCompanies.forEach {
            companyList.add(it.company!!)
        }

        val ids: ArrayList<String> = arrayListOf()
        game.similarGames.forEach {
            ids.add(it.id.toString())
        }

        setContent {
            token = dataStore.data.collectAsState(initial = BearerTokenResponse()).value
            IGDB_API.getSimilarGames(token, ids)
            gameData = IGDB_API.igSimilarGames?.observeAsState()?.value as ArrayList<IGDBGamesResponse>?
            Log.i("tokendatastore", token.toString())
            CardBlowup()
        }
    }

    @Preview
    @Composable
    fun CardBlowup() {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                if (game.artworks.isNotEmpty()) {
                    AsyncImage(
                        model = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/${game.artworks[0].imageId}.jpg",
                        contentDescription = "the background",
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                else{
                    AsyncImage(
                        model = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/${game.cover?.imageId}.jpg",
                        contentDescription = "the background",
                        Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.FillBounds
                    )
                }
                CoverAndInfo(
                    game = game,
                    genreString = genreString.joinToString(separator = "\n"),
                    platString = platString.joinToString(separator = "\n")
                )

                WebsiteIconGrid(
                    websites = game.websites,
                    viewActivity = this@CardBlowupActivity
                )
                ImageRow(
                    images = artIDs
                )
                YoutubePlayer(
                    youtubeVideoId = vidsIds,
                    lifecycleOwner = LocalLifecycleOwner.current
                )
                StoryLine(
                    storyline = game.storyline ?: "",
                    summary = game.summary ?: ""
                )

                DevsPubs(
                    companiesMap =  devsPubsPorts(game = game, companies = companyList)
                )
                GameModes(
                    game = game
                )
                PlayerPerspective(
                    game = game
                )
                AgeRating(
                    game = game
                )
                if(isParent) {
                    SimilarGamesRow(
                        games = gameData
                    )
                }
            }
        }
    }
}

@Composable
fun ImageRow(
    images: List<String>
) {
    LocalContext.current
    var selectedImageIndex by remember {
        mutableIntStateOf(0)
    }
    Box(modifier = Modifier) {
        LazyRow {
            items(images.size) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(7.dp)
                        .clickable {
                            selectedImageIndex = it
                        }
                        .background(Color.DarkGray)
                ) {
                    AsyncImage(
                        model = "https://images.igdb.com/igdb/image/upload/t_screenshot_med/${images[it]}.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                    )

                }
            }
        }
    }
}

@Composable
fun CoverAndInfo(
    game: IGDBGamesResponse,
    genreString: String,
    platString: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset(0.dp, (-48).dp)
    )
    {
        Row(
        ) {
            Column(
                modifier = Modifier.padding(start = 5.dp)
            ) {
                AsyncImage(
                    model = "https://images.igdb.com/igdb/image/upload/t_cover_big/${game.cover!!.imageId}.jpg",
                    contentDescription = "Cover Image of the current game selection",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                )
            }
            Column(
            ) {
                Text(
                    text = game.name!!,
                    color = AshWhite,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    text = "Genres: ",
                    color = AshWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = genreString,
                    color = AshWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 30.dp)
                )
                Text(
                    text = "Platforms: ",
                    color = AshWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = platString,
                    color = AshWhite,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .clickable {

                        }
                )
            }
        }
    }
}

@Composable
fun DevsPubs(
    companiesMap: Map<String, ArrayList<String>>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .offset(0.dp, (-30).dp)
            .padding(bottom = 5.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
        ) {
            if (companiesMap["Publisher(s): "]?.isNotEmpty()!!) {
                Text(
                    text = "Publisher(s): ",
                    color = AshWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = companiesMap["Publisher(s): "]?.joinToString(separator = "\n")!!,
                    color = AshWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .clickable {

                        }
                )
            }
            if (companiesMap["Developer(s): "]?.isNotEmpty()!!) {
                Text(
                    text = "Developer(s): ",
                    color = AshWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = companiesMap["Developer(s): "]?.joinToString(separator = "\n")!!,
                    color = AshWhite,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .clickable {

                        }
                )
            }
            if (companiesMap["Supporting: "]?.isNotEmpty()!!) {
                Text(
                    text = "Supporting: ",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = companiesMap["Supporting: "]?.joinToString(separator = "\n")!!,
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .clickable {

                        }
                )
            }
            if (companiesMap["Porting: "]?.isNotEmpty()!!) {
                Text(
                    text = "Porting: ",
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Text(
                    text = companiesMap["Porting: "]?.joinToString(separator = "\n")!!,
                    color = colorResource(id = R.color.ashWhite),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .clickable {

                        }
                )
            }
        }
    }
}


fun devsPubsPorts(
    game: IGDBGamesResponse,
    companies: MutableList<Companies_IGDB>
): Map<String, ArrayList<String>> {
    val devs = arrayListOf<String>()
    val ports = arrayListOf<String>()
    val pubs = arrayListOf<String>()
    val sup = arrayListOf<String>()

    game.involvedCompanies.forEach { ivc ->
        companies.forEach {
            if (ivc.company?.name == it.name) {
                if (ivc.developer!!)
                    devs.add(it.name!!)
                if (ivc.publisher!!)
                    pubs.add(it.name!!)
                if (ivc.porting!!)
                    ports.add(it.name!!)
                if (ivc.supporting!!)
                    sup.add(it.name!!)
            }
        }
    }
    return if(companies.isNotEmpty())
        mapOf(Pair("Developer(s): ", devs), Pair("Publisher(s): ", pubs), Pair("Supporting: ", sup), Pair("Porting: ", ports))
    else{
        devs.add("")
        pubs.add("")
        sup.add("")
        ports.add("")
        mapOf(Pair("Developer(s): ", devs), Pair("Publisher(s): ", pubs), Pair("Supporting: ", sup), Pair("Porting: ", ports))
    }
}


@Composable
fun StoryLine(
    storyline: String,
    summary: String
){
    var isExpanded by remember {
        mutableStateOf(false)
    }
    var hasSummary = false
    var hasStoryline = false

    if(storyline != "")
        hasStoryline = true
    if(summary != "")
        hasSummary = true

    Box(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ){
        Column()
        {
            if(hasStoryline) {
                Text(
                    text = storyline,
                    color = AshWhite,
                    maxLines = (if (isExpanded) Int.MAX_VALUE else 5),
                    fontSize = 18.sp,
                    modifier = Modifier
                )
                TextButton(onClick = { isExpanded = !isExpanded }) {
                    Text(
                        text = (if (isExpanded) "Collapse" else "Expand"),
                        color = Color.Cyan,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .offset(0.dp, (-10).dp)
                    )
                }
            }
            else if(hasSummary) {
                Text(
                    text = summary,
                    color = AshWhite,
                    maxLines = (if (isExpanded) Int.MAX_VALUE else 3),
                    fontSize = 18.sp,
                    modifier = Modifier
                )
                TextButton(onClick = { isExpanded = !isExpanded }) {
                    Text(
                        text = (if (isExpanded) "Collapse" else "Expand"),
                        color = Color.Cyan,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .offset(0.dp, (-10).dp)
                    )
                }
            }
        }
    }
}

@Composable
fun SimilarGamesRow(
    games: ArrayList<IGDBGamesResponse>?
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
    ) {
        Column {
            TextButton(onClick = {
                val intent = Intent(context, SimilarGamesGrid::class.java)
                if (games != null) {
                    intent.putExtra("gamesList", games)
                }
                context.startActivity(intent)
            }) {
                Text(
                    text = "Similar Games",
                    color = Color.Cyan,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )
            }
        }
    }
}

@Composable
fun AgeRating(
    game: IGDBGamesResponse
){
    val name = "ERSB"
    val ageRating: ArrayList<String> = arrayListOf()
    var maturityLevel: String? = null

    game.ageRatings.forEach { it ->
        if (it.category == 1){
            Log.i("ratinsID", it.rating!!.toString())
            maturityLevel = getRating(it.rating!!)
            it.contentDescriptions.forEach{
                it.description?.let { it1 -> ageRating.add(it1) }
            }
        }
    }

    Box(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
    ){
        Column {
            Text(
                text = "Age Rating: ",
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(start = 15.dp)
            )
            Text(
                text = name,
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(
                text = if(maturityLevel == null) "" else maturityLevel!!,
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(
                text = ageRating.joinToString("\n"),
                color = AshWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 30.dp)
            )
        }
    }
}

@Composable
fun PlayerPerspective(
    game: IGDBGamesResponse
){
    val perspectives: ArrayList<String> = arrayListOf()
    game.playerPerspectives.forEach {
        perspectives.add(it.name!!)
    }
    Box(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
    ){
        Column {
            Text(
                text = "Player Perspective: ",
                color = AshWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 15.dp)
            )
            Text(
                text = perspectives.joinToString(separator = "\n"),
                color = AshWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 30.dp)
            )
        }
    }
}

@Composable
fun GameModes(
    game: IGDBGamesResponse
){
    val gameModes: ArrayList<String> = arrayListOf()
    game.gameModes.forEach {
        gameModes.add(it.name!!)
    }
    Box(
        modifier = Modifier
            .offset(0.dp, (-30).dp)
    ) {
        Column {
            Text(
                text = "Game Modes: ",
                color = AshWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 15.dp)
            )
            Text(
                text = gameModes.joinToString(separator = "\n"),
                color = AshWhite,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 30.dp)
            )
        }
    }
}

fun getRating(id: Int): String {
    return when(id){
        1 -> "Three"
        2 -> "Seven"
        3 -> "Twelve"
        4 -> "Sixteen"
        5 -> "Eighteen"
        6 -> "RP"
        7 -> "EC"
        8 -> "E"
        9 -> "E16"
        10 -> "T"
        11 -> "M"
        else -> "Unknown Rating"
    }
}