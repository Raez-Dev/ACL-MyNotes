package com.raezcorp.mynotes.ui.main

import androidx.lifecycle.*
import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.domain.DeleteNoteUseCase
import com.raezcorp.mynotes.domain.GetCurrentNotesUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    val state= getCurrentNotesUseCase()

    fun onNoteDelete(it: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(it)
        }
    }
}

class MainViewModelFactory(
    private val getCurrentNotesUseCase: GetCurrentNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(getCurrentNotesUseCase,deleteNoteUseCase) as T
    }
}