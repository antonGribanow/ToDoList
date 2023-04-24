package com.antongribanow.todolist

data class Task(
    var title: String,
    var description: String,
    var isCompleted: Boolean = false,
    var imageUri: String? = null,
    var imageBase64: String? = null
)
