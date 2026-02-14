package com.quinn.to_do_list.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.quinn.to_do_list.data.local.entity.Tasks
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert
    suspend fun insert(tasks: Tasks)

    @Delete
    suspend fun delete(tasks: Tasks)

    @Update
    suspend fun updateTask(tasks: Tasks)

    @Query("SELECT * FROM tasks ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Tasks>>

    @Query("DELETE FROM tasks")
    suspend fun deleteAllTasks()
}