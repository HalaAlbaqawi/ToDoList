package com.example.ToDoList.DataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ToDoList.Model.ToDoModel


@Database(entities = [ToDoModel::class], version = 1)
abstract class ToDoDatabase : RoomDatabase(){

    abstract fun todoDao (): ToDoDao


}