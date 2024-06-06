package com.jubaya.todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.jubaya.todoapp.databinding.TodoItemLayoutBinding
import com.jubaya.todoapp.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoCheckedChangeListener, TodoEditClick {
    class TodoViewHolder(var binding: TodoItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        var binding = TodoItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent,false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        /*holder.binding.checkTask.text = todoList[position].title
        holder.binding.checkTask.isChecked = false
        holder.binding.checkTask.setOnCheckedChangeListener {
                compoundButton, b ->
            if(compoundButton.isPressed) {
                adapterOnClick(todoList[position])
            }
        }
        holder.binding.imageEdit.setOnClickListener {
            val action =
                TodoListFragmentDirections.actionEditTodo(todoList[position].uuid)

            Navigation.findNavController(it).navigate(action)
        }*/
        if (todoList[position].is_done == 0){
            holder.binding.todo = todoList[position]
            holder.binding.listener = this
            holder.binding.editListener = this
        }
    }


    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList.filter { it.is_done == 0 })
        notifyDataSetChanged()
    }

    override fun onTodoCheckedChanged(cb: CompoundButton, isChecked: Boolean, todo: Todo) {
        if(cb.isPressed){
            adapterOnClick(todo)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionEditTodo(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}
