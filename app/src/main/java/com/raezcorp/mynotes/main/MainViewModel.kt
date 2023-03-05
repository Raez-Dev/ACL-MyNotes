package com.raezcorp.mynotes.main

import androidx.lifecycle.*
import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.NotesDatabase
import kotlinx.coroutines.launch

class MainViewModel(private val db:NotesDatabase) : ViewModel() {

    val state= db.notesDao().getAll()

    fun onNoteDelete(it: Note) {
        viewModelScope.launch {
            db.notesDao().delete(it)
        }
    }
}

class MainViewModelFactory(private val db:NotesDatabase):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(db) as T
    }
}