package com.example.taskmangerapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.taskmangerapp.Task

class TaskViewModel : ViewModel(){
    private val _tasks = getTasks().toMutableStateList()
    val tasks: List<Task>
        get() = _tasks

    fun remove(item: Task) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: Task, checked: Boolean) =
        tasks.find{ it.id == item.id}?.let {task -> task.checked = checked}
}

private fun getTasks() = List(30){i -> Task(i, "") }