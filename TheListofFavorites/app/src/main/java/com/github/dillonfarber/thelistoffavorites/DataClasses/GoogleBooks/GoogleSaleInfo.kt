package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleSaleInfo(
    @SerializedName("country"     ) var country     : String?  = null,
    @SerializedName("saleability" ) var saleability : String?  = null,
    @SerializedName("isEbook"     ) var isEbook     : Boolean? = null
): Parcelable
