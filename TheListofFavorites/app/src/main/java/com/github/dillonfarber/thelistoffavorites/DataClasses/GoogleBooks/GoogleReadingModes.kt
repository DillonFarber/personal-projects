package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleReadingModes(
    @SerializedName("text"  ) var text  : Boolean? = null,
    @SerializedName("image" ) var image : Boolean? = null
): Parcelable
