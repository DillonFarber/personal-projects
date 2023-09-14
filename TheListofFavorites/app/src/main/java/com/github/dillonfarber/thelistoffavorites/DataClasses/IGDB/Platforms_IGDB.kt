package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Platforms_IGDB(
    @SerializedName("id"               ) var id              : Int?           = null,
    @SerializedName("abbreviation"     ) var abbreviation    : String?        = null,
    @SerializedName("alternative_name" ) var alternativeName : String?        = null,
    @SerializedName("category"         ) var category        : Int?           = null,
    @SerializedName("created_at"       ) var createdAt       : Int?           = null,
    @SerializedName("name"             ) var name            : String?        = null,
    @SerializedName("platform_logo"    ) var platformLogo    : Int?           = null,
    @SerializedName("slug"             ) var slug            : String?        = null,
    @SerializedName("updated_at"       ) var updatedAt       : Int?           = null,
    @SerializedName("url"              ) var url             : String?        = null,
    @SerializedName("versions"         ) var versions        : ArrayList<Int> = arrayListOf(),
    @SerializedName("websites"         ) var websites        : ArrayList<Int> = arrayListOf(),
    @SerializedName("checksum"         ) var checksum        : String?        = null
) : Parcelable
