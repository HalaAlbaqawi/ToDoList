package com.example.ToDoList.Repositories

import android.content.Context
import androidx.lifecycle.observe
import androidx.room.Query
import androidx.room.Room
import com.example.ToDoList.DataBase.ToDoDatabase
import com.example.ToDoList.Model.ToDoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import java.lang.Exception


private const val DATABASE_NAME = "todo-database"
class ToDoListRepository (context: Context) {


    private val database: ToDoDatabase =
        Room.databaseBuilder(
            context,
           ToDoDatabase::class.java,
            DATABASE_NAME ).fallbackToDestructiveMigration().build()


        private val todoDao = database.todoDao()


// calling the functions from the dao
    fun getItems(isHide: Boolean) = todoDao.getHideCompletedTasks(isHide)
    fun getSearchItems(query: String) = todoDao.getSearchItems(query)
    suspend fun addItem (todoModel:ToDoModel) = todoDao.addItem(todoModel)
    suspend fun updateItem(todoModel:ToDoModel)= todoDao.updateItem(todoModel)
    suspend fun deleteItem (todoModel:ToDoModel)= todoDao.deleteItem(todoModel)


    suspend fun deleteCompletedTask() = todoDao.deleteCompletedTask()
 // we use the companion object for ini
    companion object {

        private var instance: ToDoListRepository? = null
        fun init(context: Context){
            if (instance == null)
                instance = ToDoListRepository(context)
        }
        fun get(): ToDoListRepository {
            return instance ?: throw Exception ("Repository must be initialized")
        }
    }
}