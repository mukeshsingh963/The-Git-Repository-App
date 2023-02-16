package com.learning.thegitrepoapp.jetpack.repo.face

import androidx.lifecycle.LiveData
import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import kotlinx.coroutines.flow.Flow

interface AppRepo {

    val liveData: LiveData<List<ItemModel>>

    suspend fun delete(model: ItemModel)

    suspend fun fetchData(model: RepoRequest): ResourceState<ItemModel>

    suspend fun clear()
}