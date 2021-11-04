package com.example.ToDoList.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ToDoList.DataBase.ToDoDao
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.Repositories.ToDoListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class ToDoListViewModel : ViewModel(){

    // Getting instance from To Do List repository with companion object function
    private val todolistRepository = ToDoListRepository.get()



    fun getSearchItems(query: String) = todolistRepository.getSearchItems(query)

    var selectedItemmutableLiveData = MutableLiveData<ToDoModel>()

  fun addItem(title: String,deadline: String,details: String,creationDate: String,doneCheckBox: Boolean){
        viewModelScope.launch {
            todolistRepository.addItem(ToDoModel(title, deadline, details,creationDate,doneCheckBox))
        }
    }
   fun updateItem(toDoModel: ToDoModel){
        viewModelScope.launch {
            todolistRepository.updateItem(toDoModel)
        }
    }
    fun deleteItem(toDoModel: ToDoModel){ viewModelScope.launch {
            todolistRepository.deleteItem(toDoModel)
        }

    }
    // calling the functions from dao
    fun deleteCompletedTask() = viewModelScope.launch { todolistRepository.deleteCompletedTask() }

    fun getHideCompletedTasks(isHide: Boolean) = todolistRepository.getItems(isHide)

}
