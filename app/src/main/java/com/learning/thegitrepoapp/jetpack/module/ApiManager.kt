package com.learning.thegitrepoapp.jetpack.module

import com.learning.thegitrepoapp.jetpack.retrofit.RetrofitCalls
import retrofit2.Retrofit

class ApiManager constructor(
    private val retrofit: Retrofit
) {
    val retrofitApis by lazy { retrofit.updateBaseUrl("https://api.github.com/").createApi<RetrofitCalls>() }

}


inline fun <reified T> Retrofit.createApi(): T = this.create(T::class.java)
fun Retrofit.updateBaseUrl(baseUrl: String): Retrofit = this.newBuilder().baseUrl(baseUrl).build()