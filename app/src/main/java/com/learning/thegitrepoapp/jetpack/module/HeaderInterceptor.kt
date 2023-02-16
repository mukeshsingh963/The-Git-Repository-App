package com.learning.thegitrepoapp.jetpack.module

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return synchronized(this) {
            val originalRequest = chain.request()
            val requestBuilder =
                originalRequest.newBuilder().apply {
                    header("Content-Type", "application/json")
                        .addHeader("Accept", "application/vnd.github+json")
                        .addHeader("Authorization", "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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")
                        .addHeader("X-GitHub-Api-Version","2022-11-28")
                }
            chain.proceed(requestBuilder.build())
        }
    }
}