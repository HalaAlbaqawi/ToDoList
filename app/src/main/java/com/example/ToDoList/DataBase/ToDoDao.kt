package com.example.ToDoList.DataBase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ToDoList.Model.ToDoModel


@Dao
interface ToDoDao {


    @Insert
    suspend fun addItem(todoModel: ToDoModel)

    @Query("SELECT * FROM ToDoModel")
    fun getItems(): LiveData<List<ToDoModel>>

    @Update
    suspend fun updateItem(todoModel: ToDoModel)

    @Delete
    suspend fun deleteItem(todoModel: ToDoModel)


}