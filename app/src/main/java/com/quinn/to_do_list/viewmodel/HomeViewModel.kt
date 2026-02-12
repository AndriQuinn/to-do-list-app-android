package com.quinn.to_do_list.viewmodel

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.quinn.to_do_list.data.model.TaskNode
import com.quinn.to_do_list.data.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.io.File

class HomeViewModel(
    private val repository: TaskRepository
) : ViewModel() {

    private val _taskFile = mutableStateListOf<TaskNode>()

    val taskList: List<TaskNode> = _taskFile

    fun refresh(context: Context) {
        _taskFile.clear()
        loadFile(context)
    }

    fun addTask(taskName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTaskFile(taskName)
        }
    }

    fun deleteTask(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeTask(id)
        }
    }

    fun updateTask(task: TaskNode) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDone(task)
        }
    }

    fun clearTask() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearFile()
        }
    }

    private fun loadFile(context: Context) {
        val fileJson = repository.readFile() // Load the file content

        if (fileJson.length() > 1) {
            val fileList = mutableListOf<TaskNode>() // holds the list of tasks found in file
            for (task in 1 ..(fileJson.length()-1)) {
                try {
                    val taskNode: TaskNode = Json.Default.decodeFromString<TaskNode>(fileJson.getString(task))  // Attempt to decode
                    // Use taskNode object
                } catch (e: Exception) {
                    // Log the exception to see what's going wrong
                    Log.e("DeserializationError", "Failed to decode JSON: ${e.localizedMessage}")
                    e.printStackTrace()
                }
                fileList.add(
                    Json.Default.decodeFromString<TaskNode>(fileJson.getString(task)) // Load the content
                )
            }

            _taskFile.addAll(fileList) // Load all the contents
        }
    }
}