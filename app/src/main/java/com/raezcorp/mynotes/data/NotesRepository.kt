package com.raezcorp.mynotes.data

import com.raezcorp.mynotes.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(private val notesDataSource: INotesLocalDataSource) {
    val currentNotes : Flow<List<Note>> = notesDataSource.currentNotes

    suspend fun delete( note:Note) = notesDataSource.delete(note)

    suspend fun getById(noteId:Int):Note? = notesDataSource.getById(noteId)

    suspend fun save(note:Note) = notesDataSource.insert(note)
}