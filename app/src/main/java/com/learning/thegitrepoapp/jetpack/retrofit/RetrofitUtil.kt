package com.learning.thegitrepoapp.jetpack.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil {
    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()



    companion object {
        /**
         * Get singleton implementation of Retrofit Util to access its Data Members [RetrofitInterface] and [Gson]
         * @update : Implementing [val] datatype implementation which means Assign once
         */
        val instance: RetrofitUtil = RetrofitUtil()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setLenient()
                        .create()
                )
            ).client(
                OkHttpClient.Builder()
                    .connectTimeout(100, TimeUnit.SECONDS)
                    .readTimeout(100, TimeUnit.SECONDS)
                    .addNetworkInterceptor(provideCacheInterceptor())
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()


        fun provideCacheInterceptor(): Interceptor {
            return Interceptor { chain ->
                chain.proceed(chain.request())
            }
        }
    }
}