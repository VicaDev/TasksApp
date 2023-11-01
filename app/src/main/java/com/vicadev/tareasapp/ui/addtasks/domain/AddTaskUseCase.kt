package com.vicadev.tareasapp.ui.addtasks.domain

import com.vicadev.tareasapp.ui.addtasks.data.TaskRepository
import com.vicadev.tareasapp.ui.addtasks.ui.model.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.add(taskModel)
    }
}