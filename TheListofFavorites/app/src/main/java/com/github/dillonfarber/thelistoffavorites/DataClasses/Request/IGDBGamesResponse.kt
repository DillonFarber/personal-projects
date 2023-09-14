package com.github.dillonfarber.thelistoffavorites.DataClasses.Request


import android.os.Parcel
import android.os.Parcelable
import com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB.*
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class IGDBGamesResponse(
    @SerializedName("id"                  ) var id                 : Int?                          = null,
    @SerializedName("age_ratings"         ) var ageRatings         : ArrayList<AgeRatings_IGDB>         = arrayListOf(),
    @SerializedName("artworks"            ) var artworks           : ArrayList<Artwork_IGDB>           = arrayListOf(),
    @SerializedName("cover"               ) var cover              : Cover_IGDB?                        = Cover_IGDB(),
    @SerializedName("game_modes"          ) var gameModes          : ArrayList<GameModes_IGDB>          = arrayListOf(),
    @SerializedName("genres"              ) var genres             : ArrayList<Genres_IGDB>             = arrayListOf(),
    @SerializedName("involved_companies"  ) var involvedCompanies  : ArrayList<InvolvedCompanies_IGDB>  = arrayListOf(),
    @SerializedName("keywords"            ) var keywords           : ArrayList<Keywords_IGDB>           = arrayListOf(),
    @SerializedName("name"                ) var name               : String?                       = null,
    @SerializedName("platforms"           ) var platforms          : ArrayList<Platforms_IGDB>          = arrayListOf(),
    @SerializedName("player_perspectives" ) var playerPerspectives : ArrayList<PlayerPerspectives_IGDB> = arrayListOf(),
    @SerializedName("rating"              ) var rating             : Double?                       = null,
    @SerializedName("rating_count"        ) var ratingCount        : Int?                          = null,
    @SerializedName("screenshots"         ) var screenshots        : ArrayList<Screenshots_IGDB>        = arrayListOf(),
    @SerializedName("similar_games"       ) var similarGames       : ArrayList<SimilarGames_IGDB>       = arrayListOf(),
    @SerializedName("storyline"           ) var storyline          : String?                       = null,
    @SerializedName("summary"             ) var summary            : String?                       = null,
    @SerializedName("url"                 ) var url                : String?                       = null,
    @SerializedName("videos"              ) var videos             : ArrayList<Videos_IGDB>             = arrayListOf(),
    @SerializedName("websites"            ) var websites           : ArrayList<Websites_IGDB>           = arrayListOf()
): Parcelable
