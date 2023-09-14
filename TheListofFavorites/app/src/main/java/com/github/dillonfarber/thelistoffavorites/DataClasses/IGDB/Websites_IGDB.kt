package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Websites_IGDB(
    @SerializedName("id"       ) var id       : Int?     = null,
    @SerializedName("category" ) var category : Int?     = null,
    @SerializedName("game"     ) var game     : Int?     = null,
    @SerializedName("trusted"  ) var trusted  : Boolean? = null,
    @SerializedName("url"      ) var url      : String?  = null,
    @SerializedName("checksum" ) var checksum : String?  = null
) : Parcelable
