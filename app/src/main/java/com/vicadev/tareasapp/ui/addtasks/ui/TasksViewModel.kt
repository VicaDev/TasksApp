package com.vicadev.tareasapp.ui.addtasks.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vicadev.tareasapp.ui.addtasks.domain.AddTaskUseCase
import com.vicadev.tareasapp.ui.addtasks.domain.DeleteTaskUseCase
import com.vicadev.tareasapp.ui.addtasks.domain.GetTasksUseCase
import com.vicadev.tareasapp.ui.addtasks.domain.UpdateTaskUseCase
import com.vicadev.tareasapp.ui.addtasks.ui.TasksUiState.*
import com.vicadev.tareasapp.ui.addtasks.ui.model.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    getTasksUseCase: GetTasksUseCase

) : ViewModel() {

    val uiState: StateFlow<TasksUiState> = getTasksUseCase().map (::Success)
        .catch { Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog: LiveData<Boolean> = _showDialog

    //private val _tasks = mutableStateListOf<TaskModel>()
    //val task: List<TaskModel> = _tasks

    fun onDialogClose() {
        _showDialog.value = false
    }

    fun onTasksCreated(task: String) {
        _showDialog.value = false
        viewModelScope.launch {
            addTaskUseCase(TaskModel(task = task))
        }
    }

    fun onShowDialogClick() {
        _showDialog.value = true
    }

    fun onCheckBoxSelected(taskModel: TaskModel) {
        viewModelScope.launch {
            updateTaskUseCase(taskModel.copy(selected = !taskModel.selected))
        }
    }

    fun onItemRemoved(taskModel: TaskModel) {
        /* Borrar check
        val task = _tasks.find { it.id == taskModel.id }
        _tasks.remove(task)
        */
        viewModelScope.launch{
            deleteTaskUseCase(taskModel)
        }
    }
}


























