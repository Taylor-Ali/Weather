package com.leaf.data.di

import okhttp3.Interceptor
import okhttp3.Response

object QueryParameterInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val newUrl = originalHttpUrl.newBuilder()
            .addQueryParameter("appId", "a0e009e0ee4b984237717a383d1c9b0d")
            .addQueryParameter("cnt", 40.toString())
            .addQueryParameter("units", "metric").build()
        request.url(newUrl)
        return chain.proceed(request.build())
    }
}

