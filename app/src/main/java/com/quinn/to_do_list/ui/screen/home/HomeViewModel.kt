package com.quinn.to_do_list.ui.screen.home

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.quinn.to_do_list.data.model.TaskNode
import kotlinx.serialization.json.Json
import org.json.JSONArray
import java.io.File

class HomeViewModel : ViewModel() {

    private val _taskFile = mutableStateListOf<TaskNode>()

    val taskList: List<TaskNode> = _taskFile

    fun refresh(context: Context) {
        _taskFile.clear()
        readFile(context)
    }

    private fun readFile(context: Context) {
        val filePath = File(context.filesDir, "task-list.json") // File path

        // Check if the file exist
        val fileJson = if (filePath.exists()) {
            JSONArray(filePath.readText()) // Read the file as JSON array
        } else {JSONArray()} // Return a empty JSON array

        if (fileJson.length() > 1) {
            val fileList = mutableListOf<TaskNode>() // holds the list of tasks found in file
            for (task in (fileJson.length()-1) downTo 1) {
                fileList.add(
                    Json.decodeFromString<TaskNode>(fileJson.getString(task)) // Load the content
                )
            }

            _taskFile.addAll(fileList) // Load all the contents
        }
    }
}