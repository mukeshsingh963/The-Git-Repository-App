package com.learning.thegitrepoapp.jetpack.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import com.learning.thegitrepoapp.jetpack.model.RepoRequest
import com.learning.thegitrepoapp.jetpack.module.ResourceState
import com.learning.thegitrepoapp.jetpack.module.isApiSuccessful
import com.learning.thegitrepoapp.jetpack.repo.face.AppRepo
import com.learning.thegitrepoapp.jetpack.usecase.GetReposUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor(
    private val useCase: GetReposUseCase,
    private val repo : AppRepo
) : ViewModel() {

    val dbLiveData = repo.liveData

    val liveData = Transformations.switchMap(useCase.getData()){
        val liveData = MutableLiveData<ResourceState<ItemModel>>()
        liveData.postValue(it)
        return@switchMap liveData

    }
//    suspend fun deleteRepo(model: ItemModel) {
//        return repo.delete(model)
//    }
    fun deleteItem(item: ItemModel) = viewModelScope.launch(Dispatchers.IO) {
    repo.delete(item)
}

    fun getProduct(request: RepoRequest) =
        useCase.run(request)
    }

