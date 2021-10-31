package com.example.ToDoList.View

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ToDoList.Model.ToDoModel
import com.example.ToDoList.R

class Details_Fragment : Fragment() {

    private val toDoListViewModel: ToDoListViewModel by activityViewModels()

private lateinit var selectedView: ToDoModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleEditText: EditText = view.findViewById(R.id.title_d_edittext)
        val detailsEditText: EditText = view.findViewById(R.id.details_d_edittext)
        val deadlineEditText: EditText = view.findViewById(R.id.deadline_d_edittext)
        val deleteButton: Button = view.findViewById(R.id.delete_button)
        val updateButton: Button = view.findViewById(R.id.update_button)

        toDoListViewModel.selectedItemmutableLiveData.observe(viewLifecycleOwner , {

            titleEditText.setText(it.title)
            detailsEditText.setText(it.details)
            deadlineEditText.setText(it.deadline)
            selectedView = it
        })
        deleteButton.setOnClickListener {

            toDoListViewModel.deleteItem(selectedView)
            findNavController().popBackStack()
        }

        val datePicker = DatePickerDialog(requireActivity(), R.style.DialogTheme)
        datePicker.setTitle("Deadline")

        datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ok") { d, i ->

            val day = datePicker.datePicker.dayOfMonth
            val month = datePicker.datePicker.month
            val year = datePicker.datePicker.year

            deadlineEditText.setText("$day/$month/$year")
        }

        deadlineEditText.setOnClickListener {

            datePicker.show()

        }

        updateButton.setOnClickListener {
        selectedView.title = titleEditText.text.toString()
        selectedView.details = detailsEditText.text.toString()
        selectedView.deadline = deadlineEditText.text.toString()
            toDoListViewModel.updateItem(selectedView)
            findNavController().popBackStack()
        }

    }


}