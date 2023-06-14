package com.github.dillonfarber.thelistoffavorites

import okhttp3
import retrofit2.http.Header


class GamingAPI {

    val map = mapOf<String, String>("client_id=" to "", "client_secret" to "")


    private val clientid = "wdcga06wfwk5r6ioi0el9i89sstg6m"
    private val clientsec = "ga6xcepjh9tuucl4kjkwfof9he0bx6"

//    val (request, response, result) = "https://api.igdb.com/v4/games".httpPost()
//        .header("Client-ID" to "Client ID", "Authorization" to "Bearer access_token", "Accept" to "application/json")
//        .body("fields age_ratings,aggregated_rating,aggregated_rating_count,alternative_names,artworks," +
//                "bundles,category,checksum,collection,cover,created_at,dlcs,expanded_games,expansions,external_games," +
//                "first_release_date,follows,forks,franchise,franchises,game_engines,game_localizations,game_modes,genres,hypes," +
//                "involved_companies,keywords,language_supports,multiplayer_modes,name,parent_game,platforms,player_perspectives," +
//                "ports,rating,rating_count,release_dates,remakes,remasters,screenshots,similar_games,slug,standalone_expansions," +
//                "status,storyline,summary,tags,themes,total_rating,total_rating_count,updated_at,url,version_parent," +
//                "version_title,videos,websites;").responseString()

}