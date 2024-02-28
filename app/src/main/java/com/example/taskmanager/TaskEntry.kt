package com.example.taskmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


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

			Button(onClick = {
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

