package com.nguyen.walmart

data class Response<T>(val status: Status, val data: T?, val exception: Exception?) {
    sealed class Status {
        data object Success : Status()
        data object Failure : Status()
    }

    val failed: Boolean
        get() = status == Status.Failure
    val isSuccessful: Boolean
        get() = !failed && data != null
    val body: T
        get() = data!!

    companion object {
        fun<T> success(data: T) = Response(Status.Success, data, null)

        fun<T> failure(exception: Exception) = Response<T>(Status.Failure, null, exception)
    }
}