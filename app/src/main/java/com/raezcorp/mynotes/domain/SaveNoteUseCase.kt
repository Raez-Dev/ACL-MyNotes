package com.raezcorp.mynotes.domain

import com.raezcorp.mynotes.Note
import com.raezcorp.mynotes.data.NotesRepository
import javax.inject.Inject

class SaveNoteUseCase @Inject constructor (private val notesRepository: NotesRepository) {

    suspend operator fun invoke(note: Note) = notesRepository.save(note)

}
