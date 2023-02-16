package com.learning.thegitrepoapp.jetpack.sources.network.face

import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import org.w3c.dom.Text

interface RepoRemote {

    suspend  fun apiGet(
        request: RepoRequest
    ) : ResourceState<ItemModel>

    suspend fun cancelApiCalls()
}