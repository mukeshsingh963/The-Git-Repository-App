package com.learning.thegitrepoapp.jetpack.Dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.learning.thegitrepoapp.jetpack.model.ItemModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Transaction
    @Query("SELECT * FROM REPO")
    fun select(): LiveData<List<ItemModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: ItemModel)

    @Delete
    fun delete(model : ItemModel)
}