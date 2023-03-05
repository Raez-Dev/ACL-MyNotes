package com.raezcorp.mynotes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.raezcorp.mynotes.ui.detail.DetailActivity
import com.raezcorp.mynotes.NotesApplication
import com.raezcorp.mynotes.data.INotesLocalDataSource
import com.raezcorp.mynotes.data.NotesRepository
import com.raezcorp.mynotes.data.NotesRoomDataSource
import com.raezcorp.mynotes.databinding.ActivityMainBinding
import com.raezcorp.mynotes.domain.DeleteNoteUseCase
import com.raezcorp.mynotes.domain.GetCurrentNotesUseCase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter

    // Access to View Model with Singleton pattern inside
    private val vm by viewModels<MainViewModel> {
        val notesDatabase = (application as NotesApplication).notesDatabase
        val notesDataSource: INotesLocalDataSource = NotesRoomDataSource(notesDatabase.notesDao())
        val notesRepository = NotesRepository(notesDataSource)
        val getCurrentNotesUseCase = GetCurrentNotesUseCase(notesRepository)
        val deleteNoteUseCase  = DeleteNoteUseCase (notesRepository)

        MainViewModelFactory(getCurrentNotesUseCase, deleteNoteUseCase )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = { DetailActivity.navigate(this@MainActivity, it.id) },
                onNoteDelete = { vm.onNoteDelete(it) }
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    vm.state.collect {
                        notesAdapter.submitList(it)
                    }
                }
            }


        }
    }
}