package com.nguyen.walmart

class Client(private val service: CountryService) {
    suspend fun fetchCountries() = wrapper { service.fetchCountries() }

    private inline fun<T> wrapper(lambda: () -> T): Response<T> {
        return try {
            Response.success(lambda.invoke())
        } catch (e: Exception) {
            Response.failure(e)
        }
    }
}