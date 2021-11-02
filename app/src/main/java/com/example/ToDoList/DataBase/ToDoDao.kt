package com.example.ToDoList.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.View.SortOrder
import kotlinx.coroutines.flow.Flow


@Dao
interface ToDoDao {
    @Insert
    suspend fun addItem(todoModel: ToDoModel)

//** these query methods to query data from your app's database

    @Query("SELECT * FROM ToDoModel WHERE title LIKE '%' || :searchQuery || '%' ORDER BY deadline DESC")
    fun getSearchItems(searchQuery: String):LiveData<List<ToDoModel>>

    @Query("SELECT * FROM ToDoModel WHERE doneCheckBox != :isHide")
    fun getHideCompletedTasks(isHide: Boolean): LiveData<List<ToDoModel>>

    @Query("DELETE FROM ToDoModel WHERE doneCheckBox")
    suspend fun deleteCompletedTask()

    @Update
    suspend fun updateItem(todoModel: ToDoModel)

    @Delete
    suspend fun deleteItem(todoModel: ToDoModel)
}