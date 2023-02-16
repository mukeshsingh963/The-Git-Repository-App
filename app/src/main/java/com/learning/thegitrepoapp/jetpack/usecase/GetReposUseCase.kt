package com.learning.thegitrepoapp.jetpack.usecase

import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.module.NetworkUseCase
import com.learning.thegitrepoapp.jetpack.repo.face.AppRepo
import com.learning.thegitrepoapp.jetpack.sources.network.face.RepoRemote
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetReposUseCase @Inject constructor(
    private val remoteSource : AppRepo) : BaseUseCase<RepoRequest, ItemModel>() {


    override fun run(params: RepoRequest?): GetReposUseCase {
        launch {
            execute(remoteSource.fetchData(params!!))
        }
        return this
    }
}