package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.R

class AnimeAdapter(private val context: Context, animeArrayList: ArrayList<Anime>) :
    RecyclerView.Adapter<AnimeAdapter.ViewHolder>(){
    private val animeArrayList: ArrayList<Anime>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Anime = animeArrayList[position]
        holder.gameCover.setImageResource(model.getCoverImg())
    }

    override fun getItemCount(): Int {
        return animeArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameCover: ImageView

        init{
            gameCover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.animeArrayList = animeArrayList
    }
}