package com.learning.thegitrepoapp.jetpack.retrofit

import com.learning.thegitrepoapp.jetpack.model.ItemModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitCalls {
/** Common API Calls */
/**
 * Common [GET] call of [retrofit2.Retrofit]
 *
 * @param url Link to load information
 * @return Object is a kind of Generic implementation to return anything which will handle manually
 */
    @GET
    fun commonGetResponse( @Url url: String): Call<ItemModel>


}
