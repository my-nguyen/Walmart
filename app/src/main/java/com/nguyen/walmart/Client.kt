package com.nguyen.walmart

class Client(private val service: CountryService) {
    suspend fun fetchCountries() = safeApiCall { service.fetchCountries() }

    private inline fun<T> safeApiCall(apiCall: () -> T): MyResponse<T> {
        return try {
            MyResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            MyResponse.failure(e)
        }
    }
}