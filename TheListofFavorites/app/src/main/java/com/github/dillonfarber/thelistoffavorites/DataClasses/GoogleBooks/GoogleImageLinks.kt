package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleImageLinks(
    @SerializedName("smallThumbnail" ) var smallThumbnail : String? = null,
    @SerializedName("thumbnail"      ) var thumbnail      : String? = null,
    @SerializedName("small"          ) var small          : String? = null,
    @SerializedName("medium"         ) var medium         : String? = null,
    @SerializedName("large"          ) var large          : String? = null
): Parcelable
