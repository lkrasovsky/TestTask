package com.example.testtask.core.retorift

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}