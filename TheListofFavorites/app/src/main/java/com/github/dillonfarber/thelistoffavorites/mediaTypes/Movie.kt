package com.github.dillonfarber.thelistoffavorites.mediaTypes

class Movie (private var title: String,
             private var genre: String,
             private var lengthOfPlay: Double,
             private var releaseDate: String,
             private  var developer: String,
             private  var publisher: String,
             private var coverImg: Int){
    fun getTitle(): String {return this.title}
    fun setTitle(title: String) {this.title = title}

    fun getGenre(): String {return this.genre}
    fun setGenre(genre: String) {this.genre = genre}

    fun getLengthOfPlay(): Double {return this.lengthOfPlay}
    fun setLengthOfPlay(lop: Double) {this.lengthOfPlay = lop}

    fun getDeveloper(): String {return this.developer}
    fun setDeveloper(dev: String) {this.developer = dev}

    fun getReleaseDate(): String {return this.releaseDate}
    fun setReleaseDate(RD: String) {this.releaseDate = RD}

    fun getPublisher(): String {return this.publisher}
    fun setPublisher(publisher: String) {this.publisher = publisher}

    fun getCoverImg(): Int {return this.coverImg}
    fun setCoverImg(image: Int) {this.coverImg = image}
}