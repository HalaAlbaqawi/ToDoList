package com.example.ToDoList.Adapter

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


class ToDoAdapter (val todolist: List<ToDoModel>, val todolistViewModel: ToDoListViewModel): RecyclerView
.Adapter<ToDoAdapter.ToDoViewHolder> () {


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

        holder.titleTextView.text = todo.title
        holder.deadlineTextView.text = todo.deadline
        holder.doneCheckBox.isChecked = todo.doneCheckBox
        holder.creationDate.text = todo.creationDate



        holder.itemView.setOnClickListener {
            todolistViewModel.selectedItemmutableLiveData.postValue(todo)

            it.findNavController().navigate(R.id.action_list_Fragment_to_details_Fragment)
        }

    }
    override fun getItemCount(): Int {
        return todolist.size
    }
}

