package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Collection_IGDB(
    @SerializedName("id"         ) var id        : Int?           = null,
    @SerializedName("created_at" ) var createdAt : Int?           = null,
    @SerializedName("games"      ) var games     : ArrayList<Int> = arrayListOf(),
    @SerializedName("name"       ) var name      : String?        = null,
    @SerializedName("slug"       ) var slug      : String?        = null,
    @SerializedName("updated_at" ) var updatedAt : Int?           = null,
    @SerializedName("url"        ) var url       : String?        = null,
    @SerializedName("checksum"   ) var checksum  : String?        = null
): Parcelable
