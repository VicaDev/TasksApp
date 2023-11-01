package com.vicadev.tareasapp.ui.addtasks.ui.model

data class TaskModel(
    val id: Int = System.currentTimeMillis().hashCode(), //Devuelve el momento exacto
    val task: String,
    var selected: Boolean = false,
)