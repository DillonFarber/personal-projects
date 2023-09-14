package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class GameLocalizations_IGDB(
    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("name"       ) var name      : String? = null,
    @SerializedName("game"       ) var game      : Int?    = null,
    @SerializedName("region"     ) var region    : Int?    = null,
    @SerializedName("created_at" ) var createdAt : Int?    = null,
    @SerializedName("updated_at" ) var updatedAt : Int?    = null,
    @SerializedName("checksum"   ) var checksum  : String? = null
) : Parcelable
