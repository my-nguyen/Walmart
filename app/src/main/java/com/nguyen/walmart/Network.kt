package com.nguyen.walmart

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/"

object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service: CountryService by lazy {
        retrofit.create(CountryService::class.java)
    }

    // val client = Client(service)
    suspend fun fetchCountries(): Response<List<Country>> {
        return try {
            Response.success(service.fetchCountries())
        } catch (e: Exception) {
            Response.failure(e)
        }
    }
}