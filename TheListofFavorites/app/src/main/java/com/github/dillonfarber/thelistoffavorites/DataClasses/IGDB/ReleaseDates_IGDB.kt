package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class ReleaseDates_IGDB(
    @SerializedName("id"         ) var id        : Int?    = null,
    @SerializedName("category"   ) var category  : Int?    = null,
    @SerializedName("created_at" ) var createdAt : Int?    = null,
    @SerializedName("date"       ) var date      : Int?    = null,
    @SerializedName("game"       ) var game      : Int?    = null,
    @SerializedName("human"      ) var human     : String? = null,
    @SerializedName("m"          ) var m         : Int?    = null,
    @SerializedName("platform"   ) var platform  : Int?    = null,
    @SerializedName("region"     ) var region    : Int?    = null,
    @SerializedName("updated_at" ) var updatedAt : Int?    = null,
    @SerializedName("y"          ) var y         : Int?    = null,
    @SerializedName("checksum"   ) var checksum  : String? = null
) : Parcelable
