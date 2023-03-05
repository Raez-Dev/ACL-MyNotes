package com.raezcorp.mynotes.domain

import com.raezcorp.mynotes.data.NotesRepository

class GetNoteByIdUseCase (private val notesRepository: NotesRepository) {

    suspend operator fun invoke (noteId: Int) = notesRepository.getById(noteId)

}
