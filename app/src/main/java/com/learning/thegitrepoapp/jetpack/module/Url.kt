package com.learning.thegitrepoapp.jetpack.module

import javax.inject.Inject


class Url @Inject constructor() {

    fun getRepo(userName: String, repoName:String) : String{
        return apiUrl + "repos/$userName/$repoName"
    }
    companion object{
        const val apiUrl: String = "https://api.github.com/"
    }
}