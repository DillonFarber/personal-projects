package com.github.dillonfarber.thelistoffavorites.DataClasses.Request

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class BearerTokenResponse(
    @SerializedName("access_token" ) var accessToken : String = "",
    @SerializedName("expires_in"   ) var expiresIn   : Int    = 0,
    @SerializedName("token_type"   ) var tokenType   : String = "") : Parcelable