package com.example.ToDoList.View

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ToDoList.Adapter.ToDoAdapter
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class List_Fragment : Fragment() {

    private val toDoList = mutableListOf<ToDoModel>()
    private lateinit var adapter: ToDoAdapter

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
        val addFloatingButton: FloatingActionButton =
            view.findViewById(R.id.add_floatingActionButton)

        adapter = ToDoAdapter(toDoList, toDoListViewModel)

        listRecyclerView.adapter = adapter


        fetchDate("")
  // adding the todolist by clicking the floating button
        addFloatingButton.setOnClickListener {

            findNavController().navigate(R.id.action_list_Fragment_to_add_Fragment)


        }

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragment_task, menu)
        // finding search menu
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fetchDate(newText.orEmpty())
                return true
            }

        })
    }
// sorting by title , deadline
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_sort_by_name -> {
                toDoList.sortBy {
                    it.title
                }
                adapter.notifyDataSetChanged()

                true
            }
            R.id.action_sort_by_date_created -> {
                toDoList.sortBy {
                    it.deadline
                }
                adapter.notifyDataSetChanged()


                true


            }
            R.id.action_hide_completed_tasks -> {
                item.isChecked = !item.isChecked

                toDoListViewModel.getHideCompletedTasks(item.isChecked)
                    .observe(viewLifecycleOwner, {
                        if (item.isChecked) {
                            toDoList.clear()
                            toDoList.addAll(it)
                            adapter.notifyDataSetChanged()
                        } else {
                            fetchDate("")
                        }
                    })
                true
            }
            R.id.action_delete_all_completed_tasks -> {
                toDoListViewModel.deleteCompletedTask()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

// To update the data in search bar and it keeps updating the result
    fun fetchDate(query: String) {
        toDoListViewModel.getSearchItems(query).observe(viewLifecycleOwner, {

            toDoList.clear()
            toDoList.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }
}