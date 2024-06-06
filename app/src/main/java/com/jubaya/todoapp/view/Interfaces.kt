package com.jubaya.todoapp.view

import android.view.View
import android.widget.CompoundButton
import com.jubaya.todoapp.model.Todo

interface TodoCheckedChangeListener {
    fun onTodoCheckedChanged(cb: CompoundButton, isChecked: Boolean, todo: Todo)
}

interface TodoEditClick {
    fun onTodoEditClick(v: View)
}

interface RadioClickListener {
    fun onRadioClick(v: View)
}
