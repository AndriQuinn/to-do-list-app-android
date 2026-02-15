package com.quinn.to_do_list.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Tasks(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val taskName: String,
    var done: Boolean = false
)
