package com.raezcorp.mynotes.domain

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.data.NotesRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentNotesUseCase  (private val notesRepository: NotesRepository) {

    operator fun  invoke(): Flow<List<Note>> = notesRepository.currentNotes

}
