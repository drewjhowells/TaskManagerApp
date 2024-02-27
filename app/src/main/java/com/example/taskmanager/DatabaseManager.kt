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
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.isNotNull
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction


@Composable
fun DatabaseManager() {

	var dbList : List<String> = TaskListTable.selectAll().map { row ->
			row[TaskListTable.name]
	}
	var taskList by remember {
		mutableStateOf(dbList)
	}
	var taskName by remember {
		mutableStateOf("")
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
					TaskListTable.insert {
						it[name] = taskName
						it[isCompleted] = false
					}
					taskName = ""
				}
			}) {
				Text(text = "Add Task")
			}
		}
		TaskList(tasks = dbList)
	}
}

object TaskListTable : IntIdTable() {
	val name: Column<String> = varchar("name", 50)
	val isCompleted: Column<Boolean> = bool("isCompleted")
}

