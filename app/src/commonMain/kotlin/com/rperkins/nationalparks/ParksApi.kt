package com.rperkins.nationalparks

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.http.takeFrom
import kotlinx.serialization.list

class ParksApi(private val baseUrl: String) {
    private val parksSerializer = KotlinxSerializer().apply {
        register(Park.serializer().list)
    }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = parksSerializer
        }
    }

    suspend fun getAllParks(): List<Park> = client.get {
        endpoint("parks")
    }

    private fun HttpRequestBuilder.endpoint(endpoint: String) {
        url {
            takeFrom(baseUrl)
            encodedPath = endpoint
        }
    }
}