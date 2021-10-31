package com.example.ToDoList.View

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.Repositories.ToDoListRepository
import kotlinx.coroutines.launch

class ToDoListViewModel : ViewModel(){

    private val todolistRepository = ToDoListRepository.get()

   var gettodo  = todolistRepository.getItems()

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
}