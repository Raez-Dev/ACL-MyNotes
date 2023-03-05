package com.raezcorp.mynotes.data

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRoomDataSource @Inject constructor(private val noteDao: NoteDao) : INotesLocalDataSource {
    override val currentNotes: Flow<List<Note>> =noteDao.getAll()

    override suspend fun delete(note: Note) = noteDao.delete(note)

    override suspend fun getById(noteId: Int): Note? = noteDao.getById(noteId)

    override suspend fun insert(note: Note) = noteDao.insert(note)
}