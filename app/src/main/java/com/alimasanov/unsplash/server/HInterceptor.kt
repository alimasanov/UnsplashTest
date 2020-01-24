package com.alimasanov.unsplash.server

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class HInterceptor(private val clientId: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Client-ID $clientId")
            .build()
        return chain.proceed(request)
    }

}