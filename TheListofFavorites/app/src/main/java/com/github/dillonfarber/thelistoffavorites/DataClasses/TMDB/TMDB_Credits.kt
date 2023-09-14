package com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TMDB_Credits(
    @SerializedName("cast" ) var cast : ArrayList<TMDB_Cast> = arrayListOf(),
    @SerializedName("crew" ) var crew : ArrayList<TMDB_Crew> = arrayListOf()
): Parcelable
