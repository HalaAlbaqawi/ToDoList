package com.example.ToDoList.Repositories

import android.content.Context
import androidx.lifecycle.observe
import androidx.room.Query
import androidx.room.Room
import com.example.ToDoList.DataBase.ToDoDatabase
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.View.SortOrder
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

        val searchQuery = MutableStateFlow("")

//    private val tasksFlow = searchQuery.flatMapLatest {
//        todoDao.getItems(it)
//
//    }


    fun getItems(isHide: Boolean) = todoDao.getHideCompletedTasks(isHide)
    fun getSearchItems(query: String) = todoDao.getSearchItems(query)
    suspend fun addItem (todoModel:ToDoModel) = todoDao.addItem(todoModel)
    suspend fun updateItem(todoModel:ToDoModel)= todoDao.updateItem(todoModel)
    suspend fun deleteItem (todoModel:ToDoModel)= todoDao.deleteItem(todoModel)
//    fun getSearchItemsWithFlowSortedByName(searchQuery: String,hideCompleted: Boolean ) = todoDao.getSearchItemsWithFlowSortedByName(searchQuery,hideCompleted)
//    fun getSearchItemsWithFlowSortedByDate(searchQuery: String,hideCompleted: Boolean ) = todoDao.getSearchItemsWithFlowSortedByDate(searchQuery,hideCompleted)
//    fun getTasks (query: String, sortOrder: SortOrder, hideCompleted: Boolean): Flow<List<ToDoModel>>
//            =
//        when(sortOrder){
//            SortOrder.BY_DATE -> getSearchItemsWithFlowSortedByDate(query, hideCompleted)
//            SortOrder.BY_NAME -> getSearchItemsWithFlowSortedByName(query, hideCompleted)
//
//        }

    suspend fun deleteCompletedTask() = todoDao.deleteCompletedTask()

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