package com.raezcorp.mynotes.ui.main

import androidx.lifecycle.*
import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.domain.DeleteNoteUseCase
import com.raezcorp.mynotes.domain.GetCurrentNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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