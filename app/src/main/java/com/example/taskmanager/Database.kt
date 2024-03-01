package com.example.taskmanager

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

//Database class using Task entities
@Database(entities = [Task::class], version = 1)
abstract class Database : RoomDatabase() {
	abstract fun taskDao(): TaskDao
}

//Dao interface for interacting with database
@Dao
interface TaskDao {
	//Define SQL functions to use by DAO object
	@Query("SELECT * FROM task")
	fun getAll(): List<Task>
	@Query("SELECT * FROM task WHERE taskName = :taskName")
	fun getTaskByName(taskName: String): Task?
	@Insert
	fun insertAll(vararg tasks: Task)
	@Update(entity = Task::class)
	fun updateTask(task : Task)
	@Delete
	fun delete(task: Task)

	fun toggleCompleted(taskName: String) {
		val task = getTaskByName(taskName)
		task?.let {
			val updatedTask = it.copy(taskCompleted = !it.taskCompleted)
			updateTask(updatedTask)
		}
	}
}

//Table definition for Task Entities
@Entity
data class Task(
	@PrimaryKey val taskName: String,
	@ColumnInfo(name = "task_completed") val taskCompleted: Boolean = false
)