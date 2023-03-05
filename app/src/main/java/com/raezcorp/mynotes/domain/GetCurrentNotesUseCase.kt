package com.raezcorp.mynotes.domain

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.data.NotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentNotesUseCase  @Inject constructor  (private val notesRepository: NotesRepository) {

    operator fun  invoke(): Flow<List<Note>> = notesRepository.currentNotes

}
