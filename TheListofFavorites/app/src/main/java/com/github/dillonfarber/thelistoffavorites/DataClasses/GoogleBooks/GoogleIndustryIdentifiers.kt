package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleIndustryIdentifiers(
    @SerializedName("type"       ) var type       : String? = null,
    @SerializedName("identifier" ) var identifier : String? = null
): Parcelable
