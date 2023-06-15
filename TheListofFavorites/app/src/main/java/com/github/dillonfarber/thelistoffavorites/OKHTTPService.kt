package com.github.dillonfarber.thelistoffavorites

import okhttp3.*
import org.json.JSONObject

class OKHTTPService {
    private val JSON = MediaType.get("application/json; charset=utf8")
    private val client = OkHttpClient()



    fun getRequest(url: String, headers: Headers): String? {
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .build()

        return try{
            val response = client.newCall(request).execute()
            response.body()?.string()
        } catch (error: java.lang.Exception){
            null
        }
    }


    fun postRequest(url: String, json: String, header: String): String? {
        val body = RequestBody.create(JSON, json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        return try{
            val response = client.newCall(request).execute()
            response.body()?.string()
        } catch (error: java.lang.Exception){
            null
        }
    }





}