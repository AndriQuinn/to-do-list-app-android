package com.quinn.to_do_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskNode (
    val taskName: String,
    val id: String
)