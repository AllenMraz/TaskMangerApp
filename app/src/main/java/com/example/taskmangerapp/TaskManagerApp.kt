package com.example.taskmangerapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun TaskManagerApp(modifier: Modifier = Modifier,
                   taskViewModel: TaskViewModel = viewModel()
){
    Column(modifier = modifier){
        TaskList(
            list = taskViewModel.tasks,
            onCheckedTask = { task, checked ->
               taskViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task ->
                taskViewModel.remove(task)
            }
        )
    }

}



@Composable
fun TaskManagerCard(taskName: String,
                    checked: Boolean,
                    onCheckedChange: (Boolean) -> Unit,
                    onClose: () -> Unit,
                    modifier : Modifier = Modifier){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp),
            text = taskName)
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose){
            Icon(Icons.Filled.Close,  contentDescription = "Close")
        }
    }

}

@Composable
fun TaskList(list: List<Task>,
             onCheckedTask: (Task, Boolean) -> Unit,
             onCloseTask: (Task) -> Unit,
             modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(list, key = { task -> task.id}){ task ->
            TaskManagerCard(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked)},
                onClose = {onCloseTask(task)}
            )

        }
    }
}
