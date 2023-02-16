package com.learning.thegitrepoapp.jetpack.sources.network.impls

import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import com.learning.thegitrepoapp.jetpack.retrofit.RetrofitCalls
import com.learning.thegitrepoapp.jetpack.module.Url
import com.learning.thegitrepoapp.jetpack.module.mapToEntity
import com.learning.thegitrepoapp.jetpack.sources.network.face.RepoRemote
import retrofit2.Call
import javax.inject.Inject

class RepoRemoteImpl @Inject constructor(
    val retrofitCalls: RetrofitCalls,
    val urls: Url
) : RepoRemote {

    private lateinit var apiCallResponse: Call<ItemModel>


    /**
     * Api call to get the repo from github [ItemModel]
     * url : [Url.apiUrl]
     *
     */

    override suspend fun apiGet(request: RepoRequest): ResourceState<ItemModel> {
        apiCallResponse = retrofitCalls.commonGetResponse(
            urls.getRepo(request.userName, request.repoName)
        )
     return apiCallResponse.mapToEntity { it }
    }

    override suspend fun cancelApiCalls() {
        if (this::apiCallResponse.isInitialized)
            apiCallResponse.cancel()
    }

}