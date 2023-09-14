package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class InvolvedCompanies_IGDB(
    @SerializedName("id"         ) var id         : Int?     = null,
    @SerializedName("company"    ) var company    : Companies_IGDB?    = Companies_IGDB(),
    @SerializedName("developer"  ) var developer  : Boolean? = null,
    @SerializedName("porting"    ) var porting    : Boolean? = null,
    @SerializedName("publisher"  ) var publisher  : Boolean? = null,
    @SerializedName("supporting" ) var supporting : Boolean? = null,
) : Parcelable
