package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Expansions_IGDB(
    @SerializedName("id"                  ) var id                 : Int?           = null,
    @SerializedName("category"            ) var category           : Int?           = null,
    @SerializedName("collection"          ) var collection         : Int?           = null,
    @SerializedName("cover"               ) var cover              : Int?           = null,
    @SerializedName("created_at"          ) var createdAt          : Int?           = null,
    @SerializedName("first_release_date"  ) var firstReleaseDate   : Int?           = null,
    @SerializedName("franchises"          ) var franchises         : ArrayList<Int> = arrayListOf(),
    @SerializedName("game_modes"          ) var gameModes          : ArrayList<Int> = arrayListOf(),
    @SerializedName("genres"              ) var genres             : ArrayList<Int> = arrayListOf(),
    @SerializedName("hypes"               ) var hypes              : Int?           = null,
    @SerializedName("keywords"            ) var keywords           : ArrayList<Int> = arrayListOf(),
    @SerializedName("multiplayer_modes"   ) var multiplayerModes   : ArrayList<Int> = arrayListOf(),
    @SerializedName("name"                ) var name               : String?        = null,
    @SerializedName("parent_game"         ) var parentGame         : Int?           = null,
    @SerializedName("platforms"           ) var platforms          : ArrayList<Int> = arrayListOf(),
    @SerializedName("player_perspectives" ) var playerPerspectives : ArrayList<Int> = arrayListOf(),
    @SerializedName("release_dates"       ) var releaseDates       : ArrayList<Int> = arrayListOf(),
    @SerializedName("similar_games"       ) var similarGames       : ArrayList<Int> = arrayListOf(),
    @SerializedName("slug"                ) var slug               : String?        = null,
    @SerializedName("summary"             ) var summary            : String?        = null,
    @SerializedName("tags"                ) var tags               : ArrayList<Int> = arrayListOf(),
    @SerializedName("themes"              ) var themes             : ArrayList<Int> = arrayListOf(),
    @SerializedName("updated_at"          ) var updatedAt          : Int?           = null,
    @SerializedName("url"                 ) var url                : String?        = null,
    @SerializedName("videos"              ) var videos             : ArrayList<Int> = arrayListOf(),
    @SerializedName("checksum"            ) var checksum           : String?        = null
) : Parcelable
