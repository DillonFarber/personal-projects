package com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TMDB_Configuration(
    @SerializedName("images"      ) var images     : TMDB_ImageDetails?           = TMDB_ImageDetails(),
    @SerializedName("change_keys" ) var changeKeys : ArrayList<String> = arrayListOf()
): Parcelable
