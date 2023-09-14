package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AgeRatings_IGDB(
    @SerializedName("id"                   ) var id                  : Int?           = null,
    @SerializedName("category"             ) var category            : Int?           = null,
    @SerializedName("content_descriptions" ) var contentDescriptions : ArrayList<ContentDescription_AR_IGDB> = arrayListOf(),
    @SerializedName("rating"               ) var rating              : Int?           = null
): Parcelable
