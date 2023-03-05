package com.raezcorp.mynotes.domain

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.data.NotesRepository

class DeleteNoteUseCase (private val notesRepository: NotesRepository) {

    suspend operator fun invoke (note: Note) = notesRepository.delete(note)

}
