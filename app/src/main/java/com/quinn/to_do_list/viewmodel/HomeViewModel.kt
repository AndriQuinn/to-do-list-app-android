package com.quinn.to_do_list.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quinn.to_do_list.data.local.entity.Tasks
import com.quinn.to_do_list.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository) : ViewModel() {

    var taskName by  mutableStateOf("")
        private set
    val tasks: Flow<List<Tasks>> = repository.readAllTask()


    fun onTextChange(newText: String) {
        taskName = newText
    }

    fun addTask() {
        if (taskName.isEmpty()) {
            taskName = "Untitled Task"
        }

        viewModelScope.launch {
            repository.addTask(taskName)
        }

        taskName = ""
    }

    fun removeTask(task: Tasks) {
        task.done = !task.done
        viewModelScope.launch {
            repository.removeTask(task)
        }
    }

    fun updateTask(task: Tasks) {
        task.done = !task.done
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun removeAllTask() {
        viewModelScope.launch {
            repository.removeAllTask()
        }
    }
}