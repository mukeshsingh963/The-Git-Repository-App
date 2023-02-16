package com.learning.thegitrepoapp.jetpack.sources.data.face

import androidx.lifecycle.LiveData
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface RepoData {

    val liveData: LiveData<List<ItemModel>>

    suspend fun dbInsert(model: ItemModel)

    suspend fun dbDelete(model: ItemModel)
}