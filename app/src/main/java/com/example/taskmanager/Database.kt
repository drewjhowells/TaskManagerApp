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
	@Insert
	fun insertAll(vararg tasks: Task)
	@Update(entity = Task::class)
	fun toggleCompleted(taskCompleted : Boolean)
	@Delete
	fun delete(task: Task)
}

//Table definition for Task Entities
@Entity
data class Task(
	@PrimaryKey val taskName: String,
	@ColumnInfo(name = "task_completed") val taskCompleted: Boolean = false
)