package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class MultiplayerModes_IGDB(
    @SerializedName("id"             ) var id             : Int?     = null,
    @SerializedName("campaigncoop"   ) var campaigncoop   : Boolean? = null,
    @SerializedName("dropin"         ) var dropin         : Boolean? = null,
    @SerializedName("game"           ) var game           : Int?     = null,
    @SerializedName("lancoop"        ) var lancoop        : Boolean? = null,
    @SerializedName("offlinecoop"    ) var offlinecoop    : Boolean? = null,
    @SerializedName("offlinecoopmax" ) var offlinecoopmax : Int?     = null,
    @SerializedName("offlinemax"     ) var offlinemax     : Int?     = null,
    @SerializedName("onlinecoop"     ) var onlinecoop     : Boolean? = null,
    @SerializedName("onlinecoopmax"  ) var onlinecoopmax  : Int?     = null,
    @SerializedName("onlinemax"      ) var onlinemax      : Int?     = null,
    @SerializedName("platform"       ) var platform       : Int?     = null,
    @SerializedName("splitscreen"    ) var splitscreen    : Boolean? = null,
    @SerializedName("checksum"       ) var checksum       : String?  = null
) : Parcelable
