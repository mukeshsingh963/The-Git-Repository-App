package com.learning.thegitrepoapp.jetpack.module

import com.learning.thegitrepoapp.jetpack.repo.face.AppRepo
import com.learning.thegitrepoapp.jetpack.repo.impls.AppRepoImpl
import com.learning.thegitrepoapp.jetpack.sources.network.face.RepoRemote
import com.learning.thegitrepoapp.jetpack.sources.network.impls.RepoRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepoBinder {
    @Binds
    abstract fun provideAppRepo(impl : AppRepoImpl) : AppRepo

    @Binds
    abstract fun provideRemoteRepo(impl : RepoRemoteImpl) : RepoRemote
}