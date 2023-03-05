package com.raezcorp.mynotes.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.NotesDatabase
import com.raezcorp.mynotes.data.NotesRepository
import com.raezcorp.mynotes.domain.GetNoteByIdUseCase
import com.raezcorp.mynotes.domain.SaveNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel (
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val noteId : Int = 0) : ViewModel() {

    private val _state = MutableStateFlow(Note(0,"",""))
    val state: StateFlow<Note> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            if (note != null){
                _state.value = note
            }
        }
    }

    fun save(title: String, description: String) {
        viewModelScope.launch {
            val note = _state.value.copy(title = title, description = description)
            saveNoteUseCase(note)
        }
    }
}

class DetailViewModelFactory(private val getNoteByIdUseCase: GetNoteByIdUseCase,
                             private val saveNoteUseCase: SaveNoteUseCase,private val noteId : Int ): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(getNoteByIdUseCase , saveNoteUseCase,noteId) as T
    }
}