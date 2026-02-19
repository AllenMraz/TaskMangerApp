package com.example.taskmangerapp

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskmangerapp.ui.theme.TaskMangerAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskMangerAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),

                ){
                    TaskManagerApp() // calls task manager app
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskPreview(){

    TaskMangerAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            TaskManagerApp()
        }
    }
}






