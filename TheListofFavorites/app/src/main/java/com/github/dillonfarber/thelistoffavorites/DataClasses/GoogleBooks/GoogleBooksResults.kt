package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleBooksResults(
    @SerializedName("kind"       ) var kind       : String?     = null,
    @SerializedName("id"         ) var id         : String?     = null,
    @SerializedName("etag"       ) var etag       : String?     = null,
    @SerializedName("selfLink"   ) var selfLink   : String?     = null,
    @SerializedName("volumeInfo" ) var volumeInfo : BookInfo? = BookInfo(),
    @SerializedName("saleInfo"   ) var saleInfo   : GoogleSaleInfo?   = GoogleSaleInfo(),
    @SerializedName("accessInfo" ) var accessInfo : GoogleAccessInfo? = GoogleAccessInfo(),
    @SerializedName("searchInfo" ) var searchInfo : GoogleSearchInfo? = GoogleSearchInfo()
):Parcelable
