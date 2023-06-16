package com.github.dillonfarber.thelistoffavorites

import android.widget.Toast
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
        print(request.toString())


        return try{
            val response = client.newCall(request).execute()
            response.body()?.string()
        } catch (error: java.lang.Exception){
            null
        }
    }


    fun postRequest(url: String, body: FormBody, header: String): String? {
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