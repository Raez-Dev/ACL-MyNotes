package com.raezcorp.mynotes.uicompose.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.raezcorp.mynotes.R
import com.raezcorp.mynotes.ui.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(vm: MainViewModel = hiltViewModel()) {

    val state by vm.state.collectAsState(emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(id = R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
            }
        }

    ) { padding ->
        LazyColumn(
            contentPadding = padding
        ) {
            items(state) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Text(it.title, modifier = Modifier.weight(1f))

                    IconButton(onClick = {/*TODO*/ }){
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }

                }
            }

        }
    }
}