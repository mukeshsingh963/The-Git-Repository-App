package com.learning.thegitrepoapp.jetpack.module

import com.learning.thegitrepoapp.jetpack.sources.data.face.RepoData
import com.learning.thegitrepoapp.jetpack.sources.data.impls.RepoDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataBinder {

    @Binds
    abstract fun provideRepoData(impl: RepoDataImpl) : RepoData
}