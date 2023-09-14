package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.IGDBGamesResponse
import com.github.dillonfarber.thelistoffavorites.R
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import com.github.dillonfarber.thelistoffavorites.CardBlowupActivity
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.AgeRatings_IGDB
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse

class GameAdapter(private val context: Context, gameArrayList: ArrayList<IGDBGamesResponse>, token: BearerTokenResponse) :
    RecyclerView.Adapter<GameAdapter.ViewHolder>(){
    private val gameArrayList: ArrayList<IGDBGamesResponse>
    private val token = token
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: IGDBGamesResponse = gameArrayList[position]
        Picasso.get().load("https://images.igdb.com/igdb/image/upload/t_cover_small/${model.cover?.imageId}.jpg")
            .placeholder(R.drawable.controller)
            .into(holder.cover)

        holder.cover
            .setOnClickListener(){
                val intent = Intent(context, CardBlowupActivity::class.java)
                intent.putExtra("token", token)
                intent.putExtra("gameData", gameArrayList[position])
                intent.putExtra("isParent", true)
                context.startActivity(intent)
            }
    }

    override fun getItemCount(): Int {
        return gameArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cover: ImageView
        init{
            cover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.gameArrayList = gameArrayList
    }
}

