package com.example.wizeline.data.service

import okhttp3.Interceptor
import okhttp3.Response

class GenericInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        request.headers["User-Agent"]
        return chain.proceed(request)
    }
}