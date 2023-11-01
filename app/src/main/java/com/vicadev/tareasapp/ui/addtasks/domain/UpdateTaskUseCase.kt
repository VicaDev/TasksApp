package com.vicadev.tareasapp.ui.addtasks.domain

import com.vicadev.tareasapp.ui.addtasks.data.TaskRepository
import com.vicadev.tareasapp.ui.addtasks.ui.model.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.update(taskModel)
    }
}