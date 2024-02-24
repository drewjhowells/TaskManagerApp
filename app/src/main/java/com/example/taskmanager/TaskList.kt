package com.example.taskmanager

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

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