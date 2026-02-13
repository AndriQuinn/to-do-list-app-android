package com.quinn.to_do_list

import AppDatabase
import android.app.Application
import androidx.room.Room
import com.quinn.to_do_list.data.repository.TaskRepository

class MyApp : Application() {

    // Lazy init ensures it only runs after Application is attached
    val database: AppDatabase by lazy {
        Room.databaseBuilder(
            this,                     // Application context
            AppDatabase::class.java,
            "task_database"
        ).build()
    }

    val repository: TaskRepository by lazy {
        TaskRepository(database.tasksDao())
    }
}