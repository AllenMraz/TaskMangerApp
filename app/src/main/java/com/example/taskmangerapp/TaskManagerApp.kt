package com.example.taskmangerapp


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


// the function that calls the other functions so it can build the design
@Composable
fun TaskManagerApp(modifier: Modifier = Modifier,
                   taskViewModel: TaskViewModel = viewModel()
){
    Scaffold {
        Column(
            modifier = modifier.padding(it),
                horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = modifier.padding(16.dp).fillMaxWidth(), // the top of the app
                ){
                Text(
                    text = "Task Manager",
                    style = MaterialTheme.typography.headlineLarge

                )
            }
            AddTask( //calls AddTask
                list = taskViewModel.tasks,
                onAddTask = { task -> taskViewModel.add(task) }
            )

            TaskList( // calls TaskList
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

}



// Function that lets the user enter a task name and add it to the list
@Composable
fun AddTask (list: List<Task>, onAddTask: (Task) -> Unit, modifier: Modifier = Modifier){
    var input by remember {   mutableStateOf("") } // value that will be converted into a task
    var id  by remember { mutableIntStateOf(list.size) } // value that stores the key id
    Row(modifier = modifier.padding(16.dp)) {
        TextField(
            value = input,
            onValueChange = { input = it },
            modifier = modifier
        )
        Button(onClick = {  if(input != ""){
                            onAddTask( Task(id,input))
                            input = ""
                            id = list.size + 1}
                            }, // stores the input and id into a task in the list and checks to see if input is black or not
                modifier = modifier) {
            Text("Add Task")
        }
    }
}

// Function that is used to design the individual elements in the list
@Composable
fun TaskManagerCard(taskName: String,
                    checked: Boolean,
                    onCheckedChange: (Boolean) -> Unit,
                    onClose: () -> Unit,
                    modifier: Modifier = Modifier){
    var color = MaterialTheme.colorScheme.onBackground // stores the color to be used by the text

    if(checked){ // checks to see if element has been checked and will change the color of the text
        color = MaterialTheme.colorScheme.error
    }

    Card(modifier = modifier.padding(8.dp)) {
        Row(
            modifier = modifier.fillMaxWidth()
                .clip(MaterialTheme.shapes.small)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,


            ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete")
            }
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                text = taskName,
                color = color

            )
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckedChange
            )

        }
    }

}

// Function that creates a LazyColumn and add scrollobility
@Composable
fun TaskList(list: List<Task>,
             onCheckedTask: (Task, Boolean) -> Unit,
             onCloseTask: (Task) -> Unit,
             modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(list, key = { task -> task.id}){ task ->
            TaskManagerCard( // calls TaskManagerCard to stylize the individual elements
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked)},
                onClose = {onCloseTask(task)}
            )

        }
    }
}
