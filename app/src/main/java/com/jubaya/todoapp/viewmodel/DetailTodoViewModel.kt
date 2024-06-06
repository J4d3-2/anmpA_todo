package com.jubaya.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jubaya.todoapp.model.Todo
import com.jubaya.todoapp.model.TodoDatabase
import com.jubaya.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope
{
    private val job = Job()
    val todoLD = MutableLiveData<Todo>()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun addTodo(todo:Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }

    fun update(todo: Todo){
        launch {
            buildDb(getApplication()).todoDao().updateTodo(todo)
        }
    }

    fun fetch(uuid:Int) {
        launch {
            todoLD.postValue(buildDb(getApplication()).todoDao().selectTodo(uuid))
        }
    }
}