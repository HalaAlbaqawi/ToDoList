package com.example.ToDoList.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ToDoList.Adapter.ToDoAdapter
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class List_Fragment : Fragment() {

    private val toDoList = mutableListOf<ToDoModel>()

    private val toDoListViewModel: ToDoListViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     val listRecyclerView: RecyclerView = view.findViewById(R.id.list_recyclerview)
     val addFloatingButton: FloatingActionButton = view.findViewById(R.id.add_floatingActionButton)

        val todoAdapter = ToDoAdapter(toDoList,toDoListViewModel)

        listRecyclerView.adapter = todoAdapter

        toDoListViewModel.gettodo.observe(viewLifecycleOwner , {

            toDoList.clear()
            toDoList.addAll(it)
            todoAdapter.notifyDataSetChanged()
        })

        addFloatingButton.setOnClickListener {

            findNavController().navigate(R.id.action_list_Fragment_to_add_Fragment)


        }

    }
}