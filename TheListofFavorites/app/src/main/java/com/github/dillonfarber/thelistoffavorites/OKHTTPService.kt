package com.github.dillonfarber.thelistoffavorites

import okhttp3.*
import java.io.IOException
import java.nio.channels.AsynchronousChannel


class OKHTTPService {
    private val JSON = MediaType.get("application/json; charset=utf8")
    private val client = OkHttpClient()



    fun getRequest(url: String, headers: Headers): String? {
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


    fun postRequest(url: String, body: FormBody): String? {
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        val data:
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    println(response.body!!.string())
                }
            }
        })
    }

    override fun close() {
        client
    }

    override fun isOpen(): Boolean {
        TODO("Not yet implemented")
    }


}