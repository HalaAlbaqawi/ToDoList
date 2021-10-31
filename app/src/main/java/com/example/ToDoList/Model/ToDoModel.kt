package com.example.ToDoList.Model

import android.widget.CheckBox
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ToDoModel(

    var title: String,
    var deadline: String,
    var details: String,
    var creationDate: String,
    var doneCheckBox: Boolean,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
    )