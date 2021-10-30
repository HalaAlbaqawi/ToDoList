package com.example.ToDoList.Repositories

import android.content.Context
import androidx.room.Room
import com.example.ToDoList.DataBase.ToDoDatabase
import com.example.ToDoList.Model.ToDoModel
import java.lang.Exception


private const val DATABASE_NAME = "todo-database"
class ToDoListRepository (context: Context) {


    private val database: ToDoDatabase =
        Room.databaseBuilder(
            context,
           ToDoDatabase::class.java,
            DATABASE_NAME ).fallbackToDestructiveMigration().build()


        private val todoDao = database.todoDao()
    fun getItems() = todoDao.getItems()
    suspend fun addItem (todoModel:ToDoModel) = todoDao.addItem(todoModel)
    suspend fun updateItem(todoModel:ToDoModel)= todoDao.updateItem(todoModel)
    suspend fun deleteItem (todoModel:ToDoModel)= todoDao.deleteItem(todoModel)


    companion object {

        private var instance: ToDoListRepository? = null
        fun init(context: Context){
            if (instance == null)
                instance = ToDoListRepository(context)
        }
        fun get(): ToDoListRepository {
            return instance ?: throw Exception ("")
        }
    }
}