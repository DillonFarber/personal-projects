package com.github.dillonfarber.thelistoffavorites

import android.icu.text.DateFormat
import android.media.Image
import java.util.*
import kotlin.properties.Delegates

class Game(private var title: String,
            private var genre: String,
            private var lenghtOfPlay: Double,
            private  var developer: String,
            private  var publisher: String,
            private var coverImg: Int
){


    fun getTitle(): String {return this.title}
    fun setTitle(title: String) {this.title = title}

    fun getGenre(): String {return this.genre}
    fun setGenre(genre: String) {this.genre = genre}

    fun getLengthOfPlay(): Double {return this.lenghtOfPlay}
    fun setLengthOfPlay(lop: Double) {this.lenghtOfPlay = lop}

    fun getDeveloper(): String {return this.developer}
    fun setDeveloper(dev: String) {this.developer = dev}

//    fun getReleaseDate(): Date {return this.releaseDate}
//    fun setReleaseDate(RD: Date) {this.releaseDate = RD}

    fun getPublisher(): String {return this.publisher}
    fun setPublisher(publisher: String) {this.publisher = publisher}

    fun getCoverImg(): Int {return this.coverImg}
    fun setCoverImg(image: Int) {this.coverImg = image}



}