package com.example.taskmangerapp.data

import com.example.taskmangerapp.R
import com.example.taskmangerapp.model.Task

class Datasource {
    fun loadTask(): List<Task>{
        return listOf<Task>(
            Task(R.string.task1),
            Task(R.string.task2),
            Task(R.string.task3),
            Task(R.string.task4),
        )
    }
}