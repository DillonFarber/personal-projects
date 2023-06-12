package com.github.dillonfarber.thelistoffavorites


import android.icu.text.DateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class MainListView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list_view)

        val popGames: RecyclerView = findViewById(R.id.PopularGames)

        val gameModelArrayList: ArrayList<Game> = ArrayList()
        val df: DateFormat = DateFormat.getInstance()
        gameModelArrayList.add(Game("Ghost of Tsushima",
            "RPG",
            50.0,
            "Sucker Punch Productions",
            "Sony Interactive Entertainment",
            R.drawable.ghost_of_tsushima_logo_4k_p1_240x320))

        val gameAdapter = GameAdapter(this, gameModelArrayList)

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        popGames.layoutManager = linearLayoutManager
        popGames.adapter = gameAdapter


    }
}