package com.github.dillonfarber.thelistoffavorites


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.mediaTypes.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONObject
import kotlin.collections.ArrayList


class MainListView : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list_view)

        val popGames: RecyclerView = findViewById(R.id.PopularGames)
        val popBooks: RecyclerView = findViewById(R.id.PopularBooks)
        val popMovies: RecyclerView = findViewById(R.id.PopularMovies)
        val popTVShows: RecyclerView = findViewById(R.id.PopularTVShows)
        val popAnime: RecyclerView = findViewById(R.id.PopularAnime)


        val igdbService = IGDBService.getInstance()
        Toast.makeText(this@MainListView, igdbService.getBearerToken(), Toast.LENGTH_SHORT).show()

//        val params = "search \"zelda\""
//        var bodyMap = igdbService.bodyMap
//        bodyMap["name"] = "name"
//        bodyMap["cover"] = "cover"
//        bodyMap["developer"] = "developer"
//        bodyMap["aggregated_rating_count"] = "aggregated_rating_count"
//        bodyMap["age_ratings"] = "age_ratings"
//
//        val games = igdbService.gamesLookup(params, bodyMap)
//
//        if (games != null) {
//            Toast.makeText(this@MainListView, JSONObject(games.get(0).toString())["name"].toString(), Toast.LENGTH_SHORT).show()
//        }
        val game = Game("Ghost of Tsushima",
            "RPG",
            50.0,
            "September, 9, 2020",
            "Sucker Punch Productions",
            "Sony Interactive Entertainment",
            R.drawable.ghost_of_tsushima_logo_4k_p1_240x320)

        val gameModelArrayList: ArrayList<Game> = ArrayList()
        for (i in 0..10)
            gameModelArrayList.add(game)
        val gameAdapter = GameAdapter(this, gameModelArrayList)
        val gameLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popGames.layoutManager = gameLayoutManager
        popGames.adapter = gameAdapter

        val anime = Anime("Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            "Viz Media",
            R.drawable.tokyo_ghoul)

        val animeModelArray: ArrayList<Anime> = ArrayList()
        for(i in 0..10)
            animeModelArray.add(anime)
        val animeAdapter = AnimeAdapter(this, animeModelArray)
        val animeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popAnime.layoutManager = animeLayoutManager
        popAnime.adapter = animeAdapter

        val book = Book("Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            "Viz Media",
            R.drawable.tokyo_ghoul)

        val bookModelArray: ArrayList<Book> = ArrayList()
        for(i in 0..10)
            bookModelArray.add(book)
        val bookAdaper = BookAdapter(this, bookModelArray)
        val bookLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popBooks.layoutManager = bookLayoutManager
        popBooks.adapter = bookAdaper

        val movie = Movie(
            "Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            "Viz Media",
            R.drawable.tokyo_ghoul)

        val movieModelArray: ArrayList<Movie> = ArrayList()
        for(i in 0..10)
            movieModelArray.add(movie)
        val movieAdapter = MoviesAdapter(this, movieModelArray)
        val movieLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popMovies.layoutManager = movieLayoutManager
        popMovies.adapter = movieAdapter

        val tvShow = TVShows("Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            R.drawable.tokyo_ghoul)

        val tvShowsModelArray: ArrayList<TVShows> = ArrayList()
        for(i in 0..10)
            tvShowsModelArray.add(tvShow)
        val tvShowsAdapter = TVShowsAdapter(this, tvShowsModelArray)
        val tvShowsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popTVShows.layoutManager = tvShowsLayoutManager
        popTVShows.adapter = tvShowsAdapter
    }
}