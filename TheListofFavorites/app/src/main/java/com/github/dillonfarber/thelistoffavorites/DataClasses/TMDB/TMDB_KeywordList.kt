package com.github.dillonfarber.thelistoffavorites.DataClasses.TMDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class TMDB_KeywordList(
    @SerializedName("keywords" ) var keywords : ArrayList<TMDB_Keywords> = arrayListOf()
): Parcelable
