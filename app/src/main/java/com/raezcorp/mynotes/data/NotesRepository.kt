package com.raezcorp.mynotes.data

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.NoteDao
import com.raezcorp.mynotes.NotesDatabase
import kotlinx.coroutines.flow.Flow

class NotesRepository(private val noteDao:NoteDao) {
    val currentNotes : Flow<List<Note>> = noteDao.getAll()

    suspend fun delete( note:Note) = noteDao.delete(note)

    suspend fun getById(noteId:Int):Note? = noteDao.getById(noteId)

    suspend fun save(note:Note) = noteDao.insert(note)
}