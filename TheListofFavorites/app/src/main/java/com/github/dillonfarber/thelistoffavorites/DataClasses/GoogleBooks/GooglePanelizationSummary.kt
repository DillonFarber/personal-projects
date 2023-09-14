package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GooglePanelizationSummary(
    @SerializedName("containsEpubBubbles"  ) var containsEpubBubbles  : Boolean? = null,
    @SerializedName("containsImageBubbles" ) var containsImageBubbles : Boolean? = null
): Parcelable
