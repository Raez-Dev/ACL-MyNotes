package com.raezcorp.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.raezcorp.mynotes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {


    private lateinit var database : NotesDatabase

    companion object {
        const val EXTRA_NOTE_ID = "note_id"
        fun navigate(activity: AppCompatActivity, id: Int = -1) {
            val intent = Intent(activity, DetailActivity::class.java).apply{
                putExtra(EXTRA_NOTE_ID,id)
            }
            activity.startActivity(intent)

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = (application as NotesApplication).notesDatabase

        ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)

            lifecycleScope.launch {
                val note = database.notesDao().getById(intent.getIntExtra(EXTRA_NOTE_ID,-1))

                if (note != null){
                    edtTitle.setText(note.title)
                    edtDescription.setText(note.description)
                }

                btnSave.setOnClickListener {

                    val title = edtTitle.text.toString()
                    val  description = edtDescription.text.toString()

                    lifecycleScope.launch{
                        if(note != null) {
                            database.notesDao().update(note.copy(
                                title = title,
                                description = description
                            ))
                        } else {
                            database.notesDao().insert(Note(
                                id=0,
                                title = title,
                                description = description
                            ))
                        }
                        finish()
                    }

                }
            }
        }
    }


}