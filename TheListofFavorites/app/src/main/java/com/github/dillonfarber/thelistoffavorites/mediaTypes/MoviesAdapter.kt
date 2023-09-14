package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.APIs.TMDB_API
import com.github.dillonfarber.thelistoffavorites.CardBlowupActivity
import com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB.TMDB_MovieResults
import com.github.dillonfarber.thelistoffavorites.MovieCardActivity
import com.github.dillonfarber.thelistoffavorites.R
import com.squareup.picasso.Picasso

class MoviesAdapter(private val context: Context, movieArrayList: ArrayList<TMDB_MovieResults>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>(){
    private val movieArrayList: ArrayList<TMDB_MovieResults>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: TMDB_MovieResults = movieArrayList[position]
        Picasso.get().load("https://image.tmdb.org/t/p/w185/${model.posterPath}")
            .placeholder(R.drawable.controller)
            .into(holder.cover)
        holder.cover.setOnClickListener{
            TMDB_API.getMovieDetails(model.id!!)
            val intent = Intent(context, MovieCardActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cover: ImageView

        init{
            cover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.movieArrayList = movieArrayList
    }
}

