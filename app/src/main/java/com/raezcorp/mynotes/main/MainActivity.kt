package com.raezcorp.mynotes.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.raezcorp.mynotes.detail.DetailActivity
import com.raezcorp.mynotes.NotesApplication
import com.raezcorp.mynotes.NotesDatabase
import com.raezcorp.mynotes.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notesAdapter: NotesAdapter

    // Access to View Model with Singleton pattern inside
    private val vm by viewModels<MainViewModel> {
        MainViewModelFactory(
            (application as NotesApplication).notesDatabase
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)
            notesAdapter = NotesAdapter(
                onNoteClick = {DetailActivity.navigate(this@MainActivity,it.id)},
                onNoteDelete = {vm.onNoteDelete(it)}
            )
            recyclerView.adapter = notesAdapter
            fab.setOnClickListener {
                DetailActivity.navigate(this@MainActivity)
            }

            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED){
                    vm.state.collect{
                        notesAdapter.submitList(it)
                    }
                }
            }


        }
    }
}