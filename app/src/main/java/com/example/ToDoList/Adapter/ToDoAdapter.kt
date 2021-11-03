package com.example.ToDoList.Adapter

import android.graphics.Paint
import android.icu.text.Transliterator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.R
import com.example.ToDoList.View.ToDoListViewModel
import java.text.SimpleDateFormat
import java.util.*


class ToDoAdapter (val todolist: List<ToDoModel>, val todolistViewModel: ToDoListViewModel): RecyclerView
.Adapter<ToDoAdapter.ToDoViewHolder> () {

// declaring the views from the item layout XML
    class ToDoViewHolder (view: View): RecyclerView.ViewHolder(view){

        val titleTextView: TextView = view.findViewById(R.id.title_textView)
        val deadlineTextView: TextView = view.findViewById(R.id.deadline_textView)
        val doneCheckBox: CheckBox = view.findViewById(R.id.done_check)
        val creationDate: TextView = view.findViewById(R.id.creation_date_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ToDoAdapter.ToDoViewHolder, position: Int) {
        val todo = todolist[position]

// connecting the data with its view
        holder.titleTextView.text = todo.title
        holder.deadlineTextView.text = todo.deadline
        holder.doneCheckBox.isChecked = todo.doneCheckBox
        holder.creationDate.text = "created in: ${todo.creationDate}"

        Log.d("Adapter", todo.doneCheckBox.toString())
        Log.d("Adapter", todo.toString())
        if (todo.doneCheckBox){
            holder.titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.deadlineTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }else
        {
            holder.titleTextView.paintFlags = 0
            holder.deadlineTextView.paintFlags = 0
        }

        holder.doneCheckBox.setOnClickListener {

     // if you are done with the tasks you can make it done by checking the box
        if (holder.doneCheckBox.isChecked){
            holder.titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.deadlineTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }else
        {
         holder.titleTextView.paintFlags = 0
         holder.deadlineTextView.paintFlags = 0
        }
            todo.doneCheckBox = holder.doneCheckBox.isChecked
            todolistViewModel.updateItem(todo)
        }

        // indicator for the list
        var currentDate = Date()
        val format= SimpleDateFormat("d/MM/yyyy")
        val dueDate = format.parse(todo.deadline)
        if (currentDate>dueDate)
        {
            holder.titleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.deadlineTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        holder.itemView.setOnClickListener {

            // post value to liveData to send data from the To Do list fragment to details fragment
            todolistViewModel.selectedItemmutableLiveData.postValue(todo)
            it.findNavController().navigate(R.id.action_list_Fragment_to_details_Fragment)
        }

    }
    override fun getItemCount(): Int {
        return todolist.size
    }
}

