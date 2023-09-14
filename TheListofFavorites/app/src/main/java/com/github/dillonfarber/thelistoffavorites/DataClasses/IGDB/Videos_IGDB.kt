package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Videos_IGDB(
    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("game"     ) var game     : Int?    = null,
    @SerializedName("name"     ) var name     : String? = null,
    @SerializedName("video_id" ) var videoId  : String? = null,
    @SerializedName("checksum" ) var checksum : String? = null
) : Parcelable
