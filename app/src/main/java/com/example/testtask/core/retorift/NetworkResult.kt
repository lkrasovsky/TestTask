package com.example.testtask.core.retorift

import com.example.invoices.utils.retorift.HttpException


sealed class NetworkResult<out T> {

    sealed class Success<T> : NetworkResult<T>() {

        abstract val value: T

        override fun toString() = "Success($value)"

        class Value<T>(override val value: T) : Success<T>()

        data class HttpResponse<T>(
            override val value: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val url: String? = null
        ) : Success<T>(), com.example.testtask.core.retorift.HttpResponse
    }


    sealed class Failure<E : Throwable>(open val error: E? = null) : NetworkResult<Nothing>() {

        override fun toString() = "Failure($error)"

        class Error(override val error: Throwable) : Failure<Throwable>(error)

        class HttpError(override val error: HttpException) : Failure<HttpException>(),
            HttpResponse {

            override val statusCode: Int get() = error.statusCode

            override val statusMessage: String? get() = error.statusMessage

            override val url: String? get() = error.url
        }
    }
}
