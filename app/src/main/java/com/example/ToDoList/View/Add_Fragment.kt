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
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ToDoList.R
import java.text.SimpleDateFormat
import java.util.*

class Add_Fragment : Fragment() {

    private val toDoListViewModel: ToDoListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val titleEditText: EditText = view.findViewById(R.id.title_edittext)
        val detailsEditText: EditText = view.findViewById(R.id.details_edittext)
        val deadlineEditText: EditText = view.findViewById(R.id.deadline_edittext)
        val addButton: Button = view.findViewById(R.id.add_button)

        // Creation Date fun
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())

        addButton.setOnClickListener {


            findNavController().popBackStack()
        }

        val datePicker = DatePickerDialog(requireActivity(), R.style.DialogTheme)
        datePicker.setTitle("Deadline")

        datePicker.setButton(DialogInterface.BUTTON_POSITIVE, "Ok") { d, i ->

            val day = datePicker.datePicker.dayOfMonth
            val month = datePicker.datePicker.month
            val year = datePicker.datePicker.year

            deadlineEditText.setText("$day/${month+1}/$year")
        }

        deadlineEditText.setOnClickListener {

            datePicker.show()

        }
    addButton.setOnClickListener {

    val title = titleEditText.text.toString()
    val details = detailsEditText.text.toString()
    val deadline = deadlineEditText.text.toString()

        if (title.isNotEmpty() && deadline.isNotEmpty() && details.isNotEmpty() ) {

            toDoListViewModel.addItem(title,deadline,details,currentDate,false)

            findNavController().popBackStack()
        }


    }
    }
}