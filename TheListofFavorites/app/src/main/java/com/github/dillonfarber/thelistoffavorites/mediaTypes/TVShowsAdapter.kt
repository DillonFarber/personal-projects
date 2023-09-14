package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_TVResults
import com.github.dillonfarber.thelistoffavorites.R
import com.github.dillonfarber.thelistoffavorites.TVShowCard
import com.squareup.picasso.Picasso

class TVShowsAdapter(private val context: Context, tvShowsArrayList: ArrayList<TMDB_TVResults>) :
    RecyclerView.Adapter<TVShowsAdapter.ViewHolder>(){
    private val tvShowsArrayList: ArrayList<TMDB_TVResults>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: TMDB_TVResults = tvShowsArrayList[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w185/${model.posterPath}")
            .placeholder(R.drawable.controller)
            .into(holder.cover)

        holder.cover.setOnClickListener {
            val intent = Intent(context, TVShowCard::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return tvShowsArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cover: ImageView

        init{
            cover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.tvShowsArrayList = tvShowsArrayList
    }
}

