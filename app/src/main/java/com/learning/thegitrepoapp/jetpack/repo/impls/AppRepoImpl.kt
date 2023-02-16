package com.learning.thegitrepoapp.jetpack.repo.impls

import android.util.Log
import androidx.lifecycle.LiveData
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import com.learning.thegitrepoapp.jetpack.module.isApiSuccessful
import com.learning.thegitrepoapp.jetpack.repo.face.AppRepo
import com.learning.thegitrepoapp.jetpack.sources.data.face.RepoData
import com.learning.thegitrepoapp.jetpack.sources.network.face.RepoRemote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppRepoImpl @Inject constructor(
    private val dataSource : RepoData,
    private val remoteSource: RepoRemote
) : AppRepo {

    override val liveData = dataSource.liveData

    override suspend fun  delete(model: ItemModel) {
        dataSource.dbDelete(model)
    }


    override suspend fun fetchData(model : RepoRequest): ResourceState<ItemModel> {

        val response =  remoteSource.apiGet(model)
        Log.d("jamun", response.toString())
        if (isApiSuccessful(response)){
            val output = (response as ResourceState.Success).body as ItemModel
            output.url?.let {
                dataSource.dbInsert(output)
            }
        }
        return  response
    }

    override suspend fun clear() {
        remoteSource.cancelApiCalls()
    }
}