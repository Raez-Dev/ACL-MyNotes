package com.raezcorp.mynotes.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.raezcorp.mynotes.NotesApplication
import com.raezcorp.mynotes.data.NotesRepository
import com.raezcorp.mynotes.databinding.ActivityDetailBinding
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    // Access to View Model with Singleton pattern inside
    private val vm by viewModels<DetailViewModel> {
        DetailViewModelFactory(
            NotesRepository((application as NotesApplication).notesDatabase.notesDao()),
            intent.getIntExtra(EXTRA_NOTE_ID,-1)
        )
    }

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

        ActivityDetailBinding.inflate(layoutInflater).apply {
            setContentView(root)

            btnSave.setOnClickListener {
                val title = edtTitle.text.toString()
                val  description = edtDescription.text.toString()
                vm.save(title,description)
                finish()
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect {
                        edtTitle.setText(it.title)
                        edtDescription.setText(it.description)
                    }
                }
            }
        }
    }

}