package com.raezcorp.mynotes.data

import com.raezcorp.mynotes.Note
import kotlinx.coroutines.flow.Flow

interface INotesLocalDataSource {
    val currentNotes: Flow<List<Note>>

    suspend fun delete(note: Note)

    suspend fun getById(noteId: Int): Note?

    suspend fun insert(note: Note)
}