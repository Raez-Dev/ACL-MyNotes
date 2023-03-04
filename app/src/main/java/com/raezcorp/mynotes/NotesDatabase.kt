package com.raezcorp.mynotes

import androidx.room.*

@Database(entities = [Note::class], version = 1)
abstract class NotesDatabase : RoomDatabase(){
    abstract fun notesDao(): NoteDao
}

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    suspend fun getAll():List<Note>

    @Query("SELECT * FROM note WHERE id = :id")
    suspend fun getById(id:Int):Note?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note:Note)

    @Update
    suspend fun update(note:Note)

    @Delete
    suspend fun delete(note: Note)

}
