package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GoogleAccessInfo(
    @SerializedName("country"                ) var country                : String?  = null,
    @SerializedName("viewability"            ) var viewability            : String?  = null,
    @SerializedName("embeddable"             ) var embeddable             : Boolean? = null,
    @SerializedName("publicDomain"           ) var publicDomain           : Boolean? = null,
    @SerializedName("textToSpeechPermission" ) var textToSpeechPermission : String?  = null,
    @SerializedName("epub"                   ) var epub                   : GoogleEpub?    = GoogleEpub(),
    @SerializedName("pdf"                    ) var pdf                    : GooglePdf?     = GooglePdf(),
    @SerializedName("webReaderLink"          ) var webReaderLink          : String?  = null,
    @SerializedName("accessViewStatus"       ) var accessViewStatus       : String?  = null,
    @SerializedName("quoteSharingAllowed"    ) var quoteSharingAllowed    : Boolean? = null
): Parcelable
