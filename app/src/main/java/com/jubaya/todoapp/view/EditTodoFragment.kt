package com.jubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.jubaya.todoapp.R
import com.jubaya.todoapp.databinding.FragmentCreateTodoBinding
import com.jubaya.todoapp.databinding.FragmentEditTodoBinding
import com.jubaya.todoapp.model.Todo
import com.jubaya.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment(), RadioClickListener, TodoEditClick {
    private lateinit var binding:FragmentEditTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        /*binding.txtJudulTodo.text = "Edit ToDo"
        binding.btnAdd.text = "Save ToDo Changes"*/

        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        /*binding.btnAdd.setOnClickListener {
            todo.title = binding.txtTitle.text.toString()
            todo.notes = binding.txtNotes.text.toString()
            val radio = view.findViewById<RadioButton>(binding.radioGroupPriority.checkedRadioButtonId)
            todo.priority = radio.tag.toString().toInt()
            viewModel.update(todo)
            Toast.makeText(context, "ToDo Updated!", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }*/

        binding.radioListener = this
        binding.submitListener = this

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            /*todo = it
            binding.txtTitle.setText(it.title)
            binding.txtNotes.setText(it.notes)
            when(it.priority){
                1 -> binding.radioLow.isChecked = true
                2-> binding.radioMedium.isChecked = true
                3-> binding.radioHigh.isChecked = true
            }*/
            binding.todo = it
        })
    }

    override fun onRadioClick(v: View) {
        /*binding.todo.let {
            binding.todo.priority = v.toString().toInt()
        }*/
        binding.todo!!.priority = v.toString().toInt()
    }

    override fun onTodoEditClick(v: View) {
        viewModel.update(binding.todo!!)
        Toast.makeText(context, "ToDo Updated!", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

}