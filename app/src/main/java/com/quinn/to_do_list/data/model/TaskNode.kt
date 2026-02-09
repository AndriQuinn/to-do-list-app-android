package com.quinn.to_do_list.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TaskNode (
    val id: String,
    val taskName: String
)