package com.example.taskmanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun App(db : Database) {
	//Instance of DAO object
	val taskDao = db.taskDao()
	var tasks by remember {
		mutableStateOf(taskDao.getAll())
	}
	Scaffold {paddingValue ->
		Surface(
			modifier = Modifier
				.padding(paddingValue)
				.fillMaxSize()
		) {
			Column {
				TaskEntry(tasks, taskDao)
				TaskList(tasks, taskDao)
			}
		}
	}
}