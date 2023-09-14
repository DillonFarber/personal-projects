package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class AlternativeNames_IGDB(
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("comment"  ) var comment  : String? = null,
    @SerializedName("game"     ) var game     : Int?    = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("checksum" ) var checksum : String? = null
): Parcelable
