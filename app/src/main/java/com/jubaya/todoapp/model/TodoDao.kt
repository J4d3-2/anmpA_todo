package com.jubaya.todoapp.model

import androidx.navigation.NavDeepLinkDsl
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title= :title, notes= :notes, priority= :priority WHERE uuid= :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Query("UPDATE todo SET is_done= :is_done WHERE uuid= :id")
    fun update(is_done:Int, id:Int)

    @Update
    fun updateTodo(todo:Todo)

    @Delete
    fun deleteTodo(todo:Todo)
}