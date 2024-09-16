package com.nguyen.walmart

import android.util.Log

object Repository {
    suspend fun fetchCountries(): List<Country>? {
        val request = Network.client.fetchCountries()
        Log.d("Tram", "request status: ${request.status}")
        return if (request.failed || !request.isSuccessful) null else request.body
    }
}
