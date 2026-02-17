package com.example.taskmangerapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Task(
    val id: Int,
    val label: String,
    initialChecked: Boolean = false
) {
    var checked: Boolean by mutableStateOf(initialChecked)
}