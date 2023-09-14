package com.github.dillonfarber.thelistoffavorites

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.APIs.GoogleBooks_API
import com.github.dillonfarber.thelistoffavorites.APIs.IGDB_API
import com.github.dillonfarber.thelistoffavorites.APIs.TMDB_API
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.AgeRatings_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import com.github.dillonfarber.thelistoffavorites.databinding.ActivityMainBinding
import com.github.dillonfarber.thelistoffavorites.mediaTypes.*
import kotlin.collections.ArrayList

class MainListView : AppCompatActivity()  {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list_view)
        val popGames: RecyclerView = findViewById(R.id.PopularGames)
        val popBooks: RecyclerView = findViewById(R.id.PopularBooks)
        val popMovies: RecyclerView = findViewById(R.id.PopularMovies)
        val popTVShows: RecyclerView = findViewById(R.id.PopularTVShows)
        val popAnime: RecyclerView = findViewById(R.id.PopularAnime)
        var token = BearerTokenResponse()
        var ageRatingList: ArrayList<AgeRatings_IGDB> = ArrayList()


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            token = intent.getParcelableExtra("token", BearerTokenResponse::class.java)!!
        }

        IGDB_API.getGames( token )
        IGDB_API.igGames?.observe(this){
            val gameAdapter = GameAdapter(this, it, token)
            val gameLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            popGames.layoutManager = gameLayoutManager
            popGames.adapter = gameAdapter
        }
        TMDB_API.getMovies()
        TMDB_API.tm_movies?.observe(this){
            val movieAdapter = MoviesAdapter(this, it)
            val movieLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            popMovies.layoutManager = movieLayoutManager
            popMovies.adapter = movieAdapter
        }
        TMDB_API.getTVShows()
        TMDB_API.tm_tv?.observe(this){
            val tvShowsAdapter = TVShowsAdapter(this, it)
            val tvShowsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            popTVShows.layoutManager = tvShowsLayoutManager
            popTVShows.adapter = tvShowsAdapter
        }

        TMDB_API.getAnime()
        TMDB_API.tm_animeTv?.observe(this){
            val animeTvAdapter = AnimeAdapter(this, it)
            val animeTvLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            popAnime.layoutManager = animeTvLayoutManager
            popAnime.adapter = animeTvAdapter
        }

        GoogleBooks_API.getBooks()
        GoogleBooks_API.ggl_books?.observe(this){
            val bookAdapter = BookAdapter(this, it)
            val bookLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            popBooks.layoutManager = bookLayoutManager
            popBooks.adapter = bookAdapter
        }


    }

}
