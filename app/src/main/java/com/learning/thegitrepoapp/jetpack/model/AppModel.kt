package com.learning.thegitrepoapp.jetpack.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "repo")
data class ItemModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "url")
    @SerializedName("html_url")
    var url: String = "",

    @ColumnInfo(name = "repo_name")
    @SerializedName("name")
    var repoName: String? = null,

    @ColumnInfo(name = "user_name")
    @SerializedName("full_name")
    var userName: String? = null,

    @ColumnInfo(name = "description")
    @SerializedName("description")
    var description: String? = null,

    @Ignore
    @SerializedName("message")
    var message: String? = null,

    @Ignore
    @SerializedName("documentation_url")
    var documentationUrl: String? = null,
) : Parcelable

data class RepoRequest(
    val userName: String = "",
    val repoName: String = ""
)