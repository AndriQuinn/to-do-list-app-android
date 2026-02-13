package com.quinn.to_do_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quinn.to_do_list.data.local.entity.Tasks
import com.quinn.to_do_list.data.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: TaskRepository) : ViewModel() {

    val tasks: Flow<List<Tasks>> = repository.readAllTask()

    fun addTask(taskName: String) {
        viewModelScope.launch {
            repository.addTask(taskName)
        }
    }

    fun removeTask(task: Tasks) {
        viewModelScope.launch {
            repository.removeTask(task)
        }
    }

    fun updateTask(task: Tasks) {
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