package com.example.ToDoList.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ToDoList.R
import com.example.ToDoList.Repositories.ToDoListRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ToDoListRepository.init(this)
    }
}