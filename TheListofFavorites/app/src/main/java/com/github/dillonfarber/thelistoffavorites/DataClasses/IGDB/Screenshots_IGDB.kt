package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Screenshots_IGDB(
    @SerializedName("id"            ) var id           : Int?     = null,
    @SerializedName("alpha_channel" ) var alphaChannel : Boolean? = null,
    @SerializedName("animated"      ) var animated     : Boolean? = null,
    @SerializedName("game"          ) var game         : Int?     = null,
    @SerializedName("height"        ) var height       : Int?     = null,
    @SerializedName("image_id"      ) var imageId      : String?  = null,
    @SerializedName("url"           ) var url          : String?  = null,
    @SerializedName("width"         ) var width        : Int?     = null,
    @SerializedName("checksum"      ) var checksum     : String?  = null
) : Parcelable
