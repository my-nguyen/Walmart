package com.nguyen.walmart

import android.util.Log

object Repository {
    suspend fun fetchCountries(): List<Country>? {
        val request = Network.fetchCountries()
        Log.d("MyRepository", "request status: ${request.status}")
        return if (request.isFailure || !request.isSuccess) null else request.body
    }
}
