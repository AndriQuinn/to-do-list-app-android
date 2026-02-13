package com.quinn.to_do_list.data.repository

import TasksDao
import com.quinn.to_do_list.data.local.entity.Tasks
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val dao: TasksDao
) {
    suspend fun addTask(taskName: String) = dao.insert(tasks =Tasks(taskName = taskName, done = false))

    suspend fun removeTask(task: Tasks)  = dao.delete(tasks = task)

    suspend fun updateTask(task: Tasks) = dao.updateTask(task)


    fun readAllTask(): Flow<List<Tasks>>  = dao.getAllTasks()

    suspend fun removeAllTask() = dao.deleteAllTasks()

}