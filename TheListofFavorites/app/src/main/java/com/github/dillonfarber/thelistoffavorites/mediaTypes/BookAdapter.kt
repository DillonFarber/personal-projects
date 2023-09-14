package com.github.dillonfarber.thelistoffavorites.mediaTypes

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.github.dillonfarber.thelistoffavorites.BookCardActivity
import com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks.BookInfo
import com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks.GoogleBooksResults
import com.github.dillonfarber.thelistoffavorites.R
import com.squareup.picasso.Picasso

class BookAdapter(private val context: Context, bookArrayList: ArrayList<GoogleBooksResults>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>(){
    private val bookArrayList: ArrayList<GoogleBooksResults>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: GoogleBooksResults = bookArrayList[position]
        Picasso
            .get()
            .load(model.volumeInfo?.imageLinks?.thumbnail)
            .into(holder.cover)

        holder.cover.setOnClickListener {
            val intent = Intent(context, BookCardActivity::class.java)
            Log.i("Book Info", model.volumeInfo.toString())
            intent.putExtra("bookDetails", model.volumeInfo)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bookArrayList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cover: ImageView

        init{
            cover = itemView.findViewById(R.id.coverImage)
        }
    }
    init {
        this.bookArrayList = bookArrayList
    }
}

