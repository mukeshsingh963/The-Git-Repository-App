package com.learning.thegitrepoapp.jetpack.usecase

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.learning.thegitrepoapp.jetpack.module.BaseRemotePattern
import com.learning.thegitrepoapp.jetpack.module.NetworkUseCase
import com.learning.thegitrepoapp.jetpack.module.ResourceState


abstract class BaseUseCase<P, R>(vararg val remotes : BaseRemotePattern) : NetworkUseCase<P>() {
    private val liveData by lazy { MutableLiveData<ResourceState<R>>() }

    fun execute(parameter: ResourceState<R>) {
        liveData.postValue(parameter)
    }

    open fun closeProcess() {
        for (remote in remotes){
            remote.cancelApiCalls()
        }
        cancel()
    }

    fun getData() = liveData
}

/**
 * Manage api call to get response from [BaseUseCase]
 */
fun <T> LifecycleOwner.observe(liveData: LiveData<T>?, action: (t: T) -> Unit) {
    liveData?.observe(this) { it?.let { t -> action(t) } }
}