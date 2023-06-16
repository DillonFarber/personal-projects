package com.github.dillonfarber.thelistoffavorites


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.mediaTypes.Anime
import com.github.dillonfarber.thelistoffavorites.mediaTypes.AnimeAdapter
import com.github.dillonfarber.thelistoffavorites.mediaTypes.Game
import com.github.dillonfarber.thelistoffavorites.mediaTypes.GameAdapter
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


        val game: Game = Game("Ghost of Tsushima",
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

        val anime: Anime = Anime("Tokyo Ghoul",
            "Dark Fantasy, Horror",
            24.0,
            "setp, 20, 2012",
            "Studio Bones",
            "Viz Media",
            R.drawable.tokyo_ghoul)

        val animeModelArray: ArrayList<Anime> = ArrayList()
        for(i in 0..10)
            animeModelArray.add(anime)
        val animeAdapter: AnimeAdapter = AnimeAdapter(this, animeModelArray)
        val animeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        popAnime.layoutManager = animeLayoutManager
        popAnime.adapter = animeAdapter






    }
}