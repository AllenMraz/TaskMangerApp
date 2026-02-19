package com.example.taskmangerapp

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel


class TaskViewModel : ViewModel(){
    private val _tasks = getTasks().toMutableStateList()
    val tasks: List<Task>
        get() = _tasks

    fun remove(item: Task) {
        _tasks.remove(item)
    }

    fun changeTaskChecked(item: Task, checked: Boolean) =
        tasks.find{ it.label == item.label}?.let {task -> task.checked = checked}
}

private fun getTasks() = MutableList(1){i -> Task("Dishes") }