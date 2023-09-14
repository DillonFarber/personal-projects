package com.github.dillonfarber.thelistoffavorites.DataClasses.IGDB

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Remasters_IGDB(
    @SerializedName("id"                      ) var id                    : Int?                          = null,
    @SerializedName("age_ratings"             ) var ageRatings            : ArrayList<AgeRatings_IGDB>         = arrayListOf(),
    @SerializedName("aggregated_rating"       ) var aggregatedRating      : Int?                          = null,
    @SerializedName("aggregated_rating_count" ) var aggregatedRatingCount : Int?                          = null,
    @SerializedName("alternative_names"       ) var alternativeNames      : ArrayList<AlternativeNames_IGDB>   = arrayListOf(),
    @SerializedName("artworks"                ) var artworks              : ArrayList<Int>                = arrayListOf(),
    @SerializedName("bundles"                 ) var bundles               : ArrayList<Bundles_IGDB>            = arrayListOf(),
    @SerializedName("category"                ) var category              : Int?                          = null,
    @SerializedName("collection"              ) var collection            : Collection_IGDB?                   = Collection_IGDB(),
    @SerializedName("cover"                   ) var cover                 : Int?                          = null,
    @SerializedName("created_at"              ) var createdAt             : Int?                          = null,
    @SerializedName("external_games"          ) var externalGames         : ArrayList<ExternalGames_IGDB>      = arrayListOf(),
    @SerializedName("first_release_date"      ) var firstReleaseDate      : Int?                          = null,
    @SerializedName("follows"                 ) var follows               : Int?                          = null,
    @SerializedName("franchise"               ) var franchise             : Franchise_IGDB?                    = Franchise_IGDB(),
    @SerializedName("franchises"              ) var franchises            : ArrayList<Franchises_IGDB>         = arrayListOf(),
    @SerializedName("game_engines"            ) var gameEngines           : ArrayList<GameEngines_IGDB>        = arrayListOf(),
    @SerializedName("game_modes"              ) var gameModes             : ArrayList<GameModes_IGDB>          = arrayListOf(),
    @SerializedName("genres"                  ) var genres                : ArrayList<Genres_IGDB>             = arrayListOf(),
    @SerializedName("involved_companies"      ) var involvedCompanies     : ArrayList<InvolvedCompanies_IGDB>  = arrayListOf(),
    @SerializedName("keywords"                ) var keywords              : ArrayList<Keywords_IGDB>           = arrayListOf(),
    @SerializedName("multiplayer_modes"       ) var multiplayerModes      : ArrayList<MultiplayerModes_IGDB>   = arrayListOf(),
    @SerializedName("name"                    ) var name                  : String?                       = null,
    @SerializedName("parent_game"             ) var parentGame            : ParentGame_IGDB?                   = ParentGame_IGDB(),
    @SerializedName("platforms"               ) var platforms             : ArrayList<Platforms_IGDB>          = arrayListOf(),
    @SerializedName("player_perspectives"     ) var playerPerspectives    : ArrayList<PlayerPerspectives_IGDB> = arrayListOf(),
    @SerializedName("rating"                  ) var rating                : Double?                       = null,
    @SerializedName("rating_count"            ) var ratingCount           : Int?                          = null,
    @SerializedName("release_dates"           ) var releaseDates          : ArrayList<ReleaseDates_IGDB>       = arrayListOf(),
    @SerializedName("screenshots"             ) var screenshots           : ArrayList<Screenshots_IGDB>        = arrayListOf(),
    @SerializedName("similar_games"           ) var similarGames          : ArrayList<SimilarGames_IGDB>       = arrayListOf(),
    @SerializedName("slug"                    ) var slug                  : String?                       = null,
    @SerializedName("storyline"               ) var storyline             : String?                       = null,
    @SerializedName("summary"                 ) var summary               : String?                       = null,
    @SerializedName("tags"                    ) var tags                  : ArrayList<Int>                = arrayListOf(),
    @SerializedName("themes"                  ) var themes                : ArrayList<Themes_IGDB>             = arrayListOf(),
    @SerializedName("total_rating"            ) var totalRating           : Double?                       = null,
    @SerializedName("total_rating_count"      ) var totalRatingCount      : Int?                          = null,
    @SerializedName("updated_at"              ) var updatedAt             : Int?                          = null,
    @SerializedName("url"                     ) var url                   : String?                       = null,
    @SerializedName("videos"                  ) var videos                : ArrayList<Videos_IGDB>             = arrayListOf(),
    @SerializedName("websites"                ) var websites              : ArrayList<Websites_IGDB>           = arrayListOf(),
    @SerializedName("checksum"                ) var checksum              : String?                       = null,
    @SerializedName("language_supports"       ) var languageSupports      : ArrayList<LanguageSupports_IGDB>   = arrayListOf(),
    @SerializedName("game_localizations"      ) var gameLocalizations     : ArrayList<GameLocalizations_IGDB>  = arrayListOf()
) : Parcelable
