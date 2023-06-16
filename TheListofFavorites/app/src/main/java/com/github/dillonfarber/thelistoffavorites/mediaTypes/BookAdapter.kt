package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.R

class BookAdapter(private val context: Context, bookArrayList: ArrayList<Book>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    private val bookArrayList: ArrayList<Book>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: Book = bookArrayList[position]
        holder.gameCover.setImageResource(model.getCoverImg())
    }

    override fun getItemCount(): Int {
        return bookArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val gameCover: ImageView

        init{
            gameCover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.bookArrayList = bookArrayList
    }
}
