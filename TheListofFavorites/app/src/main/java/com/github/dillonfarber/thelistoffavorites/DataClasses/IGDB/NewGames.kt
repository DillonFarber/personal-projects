package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class newGames(
    @SerializedName("id"        ) var id        : Int?                 = null,
    @SerializedName("cover"     ) var cover     : Cover_IGDB?               = Cover_IGDB(),
    @SerializedName("genres"    ) var genres    : ArrayList<Genres_IGDB>    = arrayListOf(),
    @SerializedName("name"      ) var name      : String?              = null,
    @SerializedName("platforms" ) var platforms : ArrayList<Platforms_IGDB> = arrayListOf()

) : Parcelable
