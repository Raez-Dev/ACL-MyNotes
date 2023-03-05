package com.raezcorp.mynotes.ui.main

import androidx.lifecycle.*
import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.NotesDatabase
import com.raezcorp.mynotes.data.NotesRepository
import kotlinx.coroutines.launch

class MainViewModel(private val notesRepository: NotesRepository) : ViewModel() {

    val state= notesRepository.currentNotes

    fun onNoteDelete(it: Note) {
        viewModelScope.launch {
            notesRepository.delete(it)
        }
    }
}

class MainViewModelFactory(private val notesRepository: NotesRepository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(notesRepository) as T
    }
}