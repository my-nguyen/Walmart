package com.nguyen.walmart

data class MyResponse<T>(val status: Status, val countries: T?, val exception: Exception?) {
    sealed class Status {
        data object Success : Status()
        data object Failure : Status()
    }

    val failed: Boolean
        get() = status == Status.Failure
    val isSuccessful: Boolean
        get() = !failed && countries != null
    val body: T
        get() = countries!!

    companion object {
        fun<T> success(data: T) = MyResponse(Status.Success, data, null)

        fun<T> failure(exception: Exception) = MyResponse<T>(Status.Failure, null, exception)
    }
}