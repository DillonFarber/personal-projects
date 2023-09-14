package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleBooksResponse(
    @SerializedName("kind"       ) var kind       : String?          = null,
    @SerializedName("totalItems" ) var totalItems : Int?             = null,
    @SerializedName("items"      ) var items      : ArrayList<GoogleBooksResults> = arrayListOf()
): Parcelable
