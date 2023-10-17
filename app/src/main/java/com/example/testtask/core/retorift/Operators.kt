package com.example.testtask.core.retorift


fun <T> NetworkResult<T>.asSuccess(): NetworkResult.Success<T> {
    return this as NetworkResult.Success<T>
}

fun <T, R> NetworkResult<T>.map(transform: (value: T) -> R): NetworkResult<R> {
    return when (this) {
        is NetworkResult.Success -> NetworkResult.Success.Value(transform(value))
        is NetworkResult.Failure<*> -> this
    }
}

fun <T> NetworkResult<T>.handleNetworkResult(
    successBlock: (T) -> Unit,
    failureBlock: () -> Unit
) {
    when (this) {
        is NetworkResult.Failure<*> -> {
            failureBlock()
        }

        is NetworkResult.Success<*> -> {
            successBlock(this.value as T)
        }
    }
}

