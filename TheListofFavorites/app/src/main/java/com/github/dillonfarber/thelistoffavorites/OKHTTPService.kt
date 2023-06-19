package com.github.dillonfarber.thelistoffavorites

import okhttp3.*
import java.io.IOException
import java.nio.channels.AsynchronousChannel


class OKHTTPService {
    private val JSON = MediaType.get("application/json; charset=utf8")
    private val client = OkHttpClient()



    fun getRequest(url: String, headers: Headers) {
        val request = Request.Builder()
            .url(url)
            .headers(headers)
            .build()
        val response: Response
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    response
                }
            }
        })
    }
}