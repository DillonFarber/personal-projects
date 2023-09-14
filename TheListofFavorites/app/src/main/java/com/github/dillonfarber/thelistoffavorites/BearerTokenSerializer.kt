package com.github.dillonfarber.thelistoffavorites

import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.github.dillonfarber.thelistoffavorites.DataClasses.Request.BearerTokenResponse
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object BearerTokenSerializer: Serializer<BearerTokenResponse> {
    override val defaultValue: BearerTokenResponse
        get() = BearerTokenResponse()

    override suspend fun readFrom(input: InputStream): BearerTokenResponse {
        return try {
            Json.decodeFromString(
                deserializer = BearerTokenResponse.serializer(),
                string = input.readBytes().decodeToString()
            )

        }catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: BearerTokenResponse, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = BearerTokenResponse.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}