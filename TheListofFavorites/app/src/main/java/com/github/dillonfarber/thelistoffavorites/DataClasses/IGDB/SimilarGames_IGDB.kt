package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class SimilarGames_IGDB(
    @SerializedName("id"                  ) var id                 : Int?                          = null,
    @SerializedName("cover"               ) var cover              : Cover_IGDB?                        = Cover_IGDB(),

) : Parcelable
