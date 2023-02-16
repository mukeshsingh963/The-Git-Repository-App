package com.learning.thegitrepoapp.jetpack.sources.data.impls


import com.learning.thegitrepoapp.jetpack.Dao.AppDao
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.sources.data.face.RepoData
import javax.inject.Inject

class RepoDataImpl @Inject constructor(
    private val dao: AppDao
) : RepoData {

    override val liveData = dao.select()

    override suspend fun dbInsert(model: ItemModel) {
        dao.insert(model)
    }


    override suspend fun dbDelete(model: ItemModel) {
        dao.delete(model)
    }


}