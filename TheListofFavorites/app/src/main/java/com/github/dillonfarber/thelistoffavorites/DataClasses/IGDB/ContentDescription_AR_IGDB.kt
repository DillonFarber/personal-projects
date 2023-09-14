package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ContentDescription_AR_IGDB(
    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("description" ) var description : String? = null
): Parcelable
