package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.taskmanager.ui.theme.TaskManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskManagerTheme {
                TaskEntry()
            }
        }
    }
}

@Composable
fun TaskEntry() {
    var taskName by remember {
        mutableStateOf("")
    }
    var tasks by remember {
        mutableStateOf(listOf<String>())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth()
        ) {
            TextField(
                value = taskName,
                onValueChange = { text ->
                    taskName = text
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Button(onClick = { /*TODO*/
                if (taskName.isNotBlank()) {
                    tasks = tasks + taskName
                    taskName = ""
                }
            }) {
                Text(text = "Add Task")
            }
        }
        TaskList(tasks = tasks)
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn {
        items(tasks) { task ->
            TaskItem(task = task)
            Divider()
        }
    }
}

@Composable
fun TaskItem(task: String) {
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = task,
            modifier = Modifier.weight(1f),
            textDecoration = if (isChecked) TextDecoration.LineThrough else null
        )
        Button(
            onClick = {
                isChecked = !isChecked
            }
        ) {
            Text(text = if (isChecked) "Uncheck" else "Check")
        }
    }
}