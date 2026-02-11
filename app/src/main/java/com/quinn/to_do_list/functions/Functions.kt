package com.quinn.to_do_list.functions

import android.content.Context
import com.quinn.to_do_list.data.model.TaskNode
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

fun addTaskFile(context: Context, taskName: String) {

    val filePath = File(context.filesDir, "task-list.json") // Get the file path

    // Checks if the file exist in the path, if not create one
    if (!filePath.exists()) {
        val file = JSONArray()
        val lengthObj = JSONObject().put("length","1") // Create a json object length for id's
        file.put(lengthObj)
        filePath.writeText(file.toString())
    }
    val fileContent = JSONArray(filePath.readText()) // place the file content in the variable
    var length = fileContent.getJSONObject(0).getInt("length") // Get the value of length
    length++ // Iterate length
    val taskObject = JSONObject() // Create json object to hold the task data e.g. title
    taskObject.put("taskName", taskName)
    taskObject.put("id", "$length") // Assign the id as the current length
    taskObject.put("done", "false")

    fileContent.put(taskObject) // put the task object to the json array
    fileContent.getJSONObject(0).put("length","$length") // Update the length on the file
    filePath.writeText(fileContent.toString()) // rewrite the file with the updated one
}

fun removeTask(
    context: Context,
    id: String
) {
    val filePath = File(context.filesDir,"task-list.json")
    val fileContent = JSONArray(filePath.readText())

    for (i in fileContent.length() - 1 downTo 1) {
        val task = fileContent.getJSONObject(i)
        if (task.getInt("id") == id.toInt()) {
            fileContent.remove(i)
            break
        }
    }
    filePath.writeText(fileContent.toString())
}

fun updateDone(
    context: Context,
    targetTask: TaskNode
) {
    val filePath = File(context.filesDir,"task-list.json")
    val fileContent = JSONArray(filePath.readText())

    for (i in fileContent.length() - 1 downTo 1) {
        val task = fileContent.getJSONObject(i)
        if (task.getInt("id") == targetTask.id.toInt()) {
            task.put("done",targetTask.done)
            break
        }
    }
    filePath.writeText(fileContent.toString())
}