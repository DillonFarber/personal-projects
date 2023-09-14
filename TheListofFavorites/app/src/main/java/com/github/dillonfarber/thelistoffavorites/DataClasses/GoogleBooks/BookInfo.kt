package com.github.dillonfarber.thelistoffavorites.DataClasses.GoogleBooks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class BookInfo(
    @SerializedName("title"               ) var title               : String?                        = null,
    @SerializedName("authors"             ) var authors             : ArrayList<String>              = arrayListOf(),
    @SerializedName("publisher"           ) var publisher           : String?                        = null,
    @SerializedName("publishedDate"       ) var publishedDate       : String?                        = null,
    @SerializedName("description"         ) var description         : String?                        = null,
    @SerializedName("industryIdentifiers" ) var industryIdentifiers : ArrayList<GoogleIndustryIdentifiers> = arrayListOf(),
    @SerializedName("readingModes"        ) var readingModes        : GoogleReadingModes?                  = GoogleReadingModes(),
    @SerializedName("pageCount"           ) var pageCount           : Int?                           = null,
    @SerializedName("printType"           ) var printType           : String?                        = null,
    @SerializedName("categories"          ) var categories          : ArrayList<String>              = arrayListOf(),
    @SerializedName("maturityRating"      ) var maturityRating      : String?                        = null,
    @SerializedName("allowAnonLogging"    ) var allowAnonLogging    : Boolean?                       = null,
    @SerializedName("contentVersion"      ) var contentVersion      : String?                        = null,
    @SerializedName("panelizationSummary" ) var panelizationSummary : GooglePanelizationSummary?           = GooglePanelizationSummary(),
    @SerializedName("imageLinks"          ) var imageLinks          : GoogleImageLinks?                    = GoogleImageLinks(),
    @SerializedName("language"            ) var language            : String?                        = null,
    @SerializedName("previewLink"         ) var previewLink         : String?                        = null,
    @SerializedName("infoLink"            ) var infoLink            : String?                        = null,
    @SerializedName("canonicalVolumeLink" ) var canonicalVolumeLink : String?                        = null

):Parcelable